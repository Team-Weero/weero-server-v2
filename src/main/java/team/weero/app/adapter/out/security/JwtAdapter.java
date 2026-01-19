package team.weero.app.adapter.out.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.in.web.auth.dto.response.TokenClaims;
import team.weero.app.application.port.out.auth.JwtPort;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.global.security.jwt.JwtConstants;
import team.weero.app.global.security.jwt.JwtProperties;
import team.weero.app.global.security.jwt.exception.ExpiredTokenException;
import team.weero.app.global.security.jwt.exception.InvalidTokenException;

@Component
public class JwtAdapter implements JwtPort {

  private final JwtProperties jwtProperties;

  public JwtAdapter(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }

  @Override
  public String generateAccessToken(UUID userId, String email, Authority authority) {
    return generateToken(
        userId, email, authority, jwtProperties.getAccessExp(), JwtConstants.TYPE_ACCESS);
  }

  @Override
  public String generateRefreshToken(UUID userId, String email, Authority authority) {
    return generateToken(
        userId, email, authority, jwtProperties.getRefreshExp(), JwtConstants.TYPE_REFRESH);
  }

  @Override
  public TokenClaims parseToken(String token) {
    try {
      var claims =
          Jwts.parserBuilder()
              .setSigningKey(jwtProperties.getKey())
              .build()
              .parseClaimsJws(token)
              .getBody();

      UUID userId = UUID.fromString(claims.getSubject());
      String email = claims.get(JwtConstants.CLAIM_EMAIL, String.class);
      Authority authority =
          Authority.valueOf(claims.get(JwtConstants.CLAIM_AUTHORITY, String.class));

      return new TokenClaims(userId, email, authority);
    } catch (ExpiredJwtException e) {
      throw ExpiredTokenException.INSTANCE;
    } catch (JwtException e) {
      throw InvalidTokenException.INSTANCE;
    }
  }

  @Override
  public LocalDateTime getAccessTokenExpiredAt() {
    return LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp());
  }

  @Override
  public LocalDateTime getRefreshTokenExpiredAt() {
    return LocalDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
  }

  private String generateToken(
      UUID userId, String email, Authority authority, int expiration, String tokenType) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration * 1000L);

    return Jwts.builder()
        .setHeaderParam(JwtConstants.HEADER_TOKEN_TYPE, tokenType)
        .setSubject(userId.toString())
        .claim(JwtConstants.CLAIM_EMAIL, email)
        .claim(JwtConstants.CLAIM_AUTHORITY, authority.name())
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(jwtProperties.getKey(), SignatureAlgorithm.HS512)
        .compact();
  }
}
