package team.weero.app.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team.weero.app.infrastructure.auth.AuthDetailsService;
import team.weero.app.infrastructure.exception.ExpiredJwtException;
import team.weero.app.infrastructure.exception.InvalidJwtException;
import team.weero.app.persistence.auth.entity.RefreshToken;
import team.weero.app.persistence.auth.repository.RefreshTokenRepository;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtTokenProvider {

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

    public String generateToken(String accountId, String type, Long exp) {
        return Jwts.builder()
                .signWith(secretKeySpec)
                .setSubject(accountId)
                .setHeaderParam("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String generateAccessToken(String accountId) {
        return generateToken(accountId, "access", jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String accountId) {
        String refreshToken = generateToken(accountId, "refresh", jwtProperties.getRefreshExp());

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

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
