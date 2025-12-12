package team.weero.app.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team.weero.app.core.auth.dto.response.TokenResponse;
import team.weero.app.core.auth.exception.InvalidRefreshTokenException;
import team.weero.app.core.auth.exception.RefreshTokenNotFoundException;
import team.weero.app.core.auth.spi.TokenPort;
import team.weero.app.infrastructure.auth.AuthDetailsService;
import team.weero.app.infrastructure.exception.ExpiredJwtException;
import team.weero.app.infrastructure.exception.InvalidJwtException;
import team.weero.app.persistence.auth.entity.RefreshToken;
import team.weero.app.persistence.auth.repository.RefreshTokenRepository;
import team.weero.app.persistence.student.type.StudentRole;
import team.weero.app.persistence.user.type.UserRole;

import javax.crypto.spec.SecretKeySpec;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider implements TokenPort {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final SecretKeySpec secretKeySpec;

    public JwtTokenProvider(JwtProperties jwtProperties,
                            AuthDetailsService authDetailsService,
                            RefreshTokenRepository refreshTokenRepository) {
        this.jwtProperties = jwtProperties;
        this.authDetailsService = authDetailsService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.secretKeySpec = new SecretKeySpec(
                jwtProperties.getSecretKey().getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }

    @Override
    public TokenResponse generateTokenResponse(String subject, UserRole userRole, StudentRole studentRole) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String accessToken = generateAccessToken(subject, userRole, studentRole);
        String refreshToken = generateRefreshToken(subject, userRole, studentRole);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                .build();
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        String newAccessToken = refreshAccessToken(refreshToken);
        String newRefreshToken = reissueRefreshToken(refreshToken);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(newRefreshToken)
                .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                .deviceToken(null)
                .build();
    }

    private String generateToken(String accountId, String type, Long exp, UserRole userRole, StudentRole studentRole) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userRole", userRole.name());
        if (studentRole != null) {
            claims.put("studentRole", studentRole.name());
        }

        return Jwts.builder()
                .signWith(secretKeySpec)
                .setSubject(accountId)
                .setHeaderParam("type", type)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    private String generateAccessToken(String accountId, UserRole userRole, StudentRole studentRole) {
        return generateToken(accountId, "access", jwtProperties.getAccessExp(), userRole, studentRole);
    }

    private String generateRefreshToken(String accountId, UserRole userRole, StudentRole studentRole) {
        String refreshToken = generateToken(accountId, "refresh", jwtProperties.getRefreshExp(), userRole, studentRole);

        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.getRefreshExp())
                .build());

        return refreshToken;
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.replaceFirst(jwtProperties.getPrefix(), "").trim();
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearerToken);
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKeySpec)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    public UserRole getUserRoleFromToken(String token) {
        Claims claims = getTokenBody(token);
        String userRole = claims.get("userRole", String.class);
        return UserRole.valueOf(userRole);
    }

    public StudentRole getStudentRoleFromToken(String token) {
        Claims claims = getTokenBody(token);
        String studentRole = claims.get("studentRole", String.class);
        return studentRole != null ? StudentRole.valueOf(studentRole) : null;
    }

    public Authentication authentication(String token) {
        String accountId = getTokenSubject(token);
        UserRole userRole = getUserRoleFromToken(token);
        StudentRole studentRole = getStudentRoleFromToken(token);

        UserDetails userDetails = authDetailsService.loadUserByUsername(accountId, userRole, studentRole);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String refreshAccessToken(String refreshToken) {
        String accountId = getTokenSubject(refreshToken);

        RefreshToken storedToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (!storedToken.getAccountId().equals(accountId)) {
            throw new InvalidRefreshTokenException();
        }

        UserRole userRole = getUserRoleFromToken(refreshToken);
        StudentRole studentRole = getStudentRoleFromToken(refreshToken);

        return generateAccessToken(accountId, userRole, studentRole);
    }

    private String reissueRefreshToken(String oldRefreshToken) {
        String accountId = getTokenSubject(oldRefreshToken);

        RefreshToken storedToken = refreshTokenRepository.findByRefreshToken(oldRefreshToken)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (!storedToken.getAccountId().equals(accountId)) {
            throw new InvalidRefreshTokenException();
        }

        refreshTokenRepository.delete(storedToken);

        UserRole userRole = getUserRoleFromToken(oldRefreshToken);
        StudentRole studentRole = getStudentRoleFromToken(oldRefreshToken);

        return generateRefreshToken(accountId, userRole, studentRole);
    }
}
