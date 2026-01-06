package team.weero.app.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team.weero.app.domain.auth.exception.RefreshTokenNotFoundException;
import team.weero.app.infrastructure.security.auth.AuthDetailsService;
import team.weero.app.infrastructure.error.exception.ExpiredJwtException;
import team.weero.app.infrastructure.error.exception.InvalidJwtException;
import team.weero.app.domain.auth.model.RefreshToken;
import team.weero.app.application.port.out.auth.RefreshTokenPort;
import team.weero.app.domain.student.model.StudentRole;
import team.weero.app.domain.user.model.UserRole;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenPort refreshTokenPort;
    private final SecretKeySpec secretKeySpec;

    public JwtTokenProvider(JwtProperties jwtProperties,
                            AuthDetailsService authDetailsService,
                            RefreshTokenPort refreshTokenPort) {
        this.jwtProperties = jwtProperties;
        this.authDetailsService = authDetailsService;
        this.refreshTokenPort = refreshTokenPort;
        this.secretKeySpec = new SecretKeySpec(
                jwtProperties.getSecretKey().getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }

    public String generateToken(String accountId, String type, Long exp, UserRole userRole, StudentRole studentRole) {
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

    public String generateAccessToken(String accountId, UserRole userRole, StudentRole studentRole) {
        return generateToken(accountId, "access", jwtProperties.getAccessExp(), userRole, studentRole);
    }

    public String generateRefreshToken(String accountId, UserRole userRole, StudentRole studentRole) {
        String refreshToken = generateToken(accountId, "refresh", jwtProperties.getRefreshExp(), userRole, studentRole);

        refreshTokenPort.save(RefreshToken.builder()
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

    public String refreshAccessToken(String refreshToken) {
        String accountId = getTokenSubject(refreshToken);

        RefreshToken storedToken = refreshTokenPort.findByRefreshToken(refreshToken)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (!storedToken.getAccountId().equals(accountId)) {
            throw new team.weero.app.domain.auth.exception.InvalidRefreshTokenException();
        }

        UserRole userRole = getUserRoleFromToken(refreshToken);
        StudentRole studentRole = getStudentRoleFromToken(refreshToken);

        return generateAccessToken(accountId, userRole, studentRole);
    }

    public String reissueRefreshToken(String oldRefreshToken) {
        String accountId = getTokenSubject(oldRefreshToken);

        RefreshToken storedToken = refreshTokenPort.findByRefreshToken(oldRefreshToken)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (!storedToken.getAccountId().equals(accountId)) {
            throw new team.weero.app.domain.auth.exception.InvalidRefreshTokenException();
        }

        refreshTokenPort.deleteByAccountId(storedToken.getAccountId());

        UserRole userRole = getUserRoleFromToken(oldRefreshToken);
        StudentRole studentRole = getStudentRoleFromToken(oldRefreshToken);

        return generateRefreshToken(accountId, userRole, studentRole);
    }
}
