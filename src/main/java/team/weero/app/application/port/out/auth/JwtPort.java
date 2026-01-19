package team.weero.app.application.port.out.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import team.weero.app.adapter.in.web.auth.dto.response.TokenClaims;
import team.weero.app.domain.auth.type.Authority;

public interface JwtPort {

  String generateAccessToken(UUID userId, String email, Authority authority);

  String generateRefreshToken(UUID userId, String email, Authority authority);

  TokenClaims parseToken(String token);

  LocalDateTime getAccessTokenExpiredAt();

  LocalDateTime getRefreshTokenExpiredAt();
}
