package team.weero.app.domain.auth;

import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public class RefreshToken {

  private final String token;
  private final UUID userId;
  private final Authority authority;
  private final LocalDateTime expirationTime;

  public RefreshToken(
      String token, UUID userId, Authority authority, LocalDateTime expirationTime) {
    this.token = token;
    this.userId = userId;
    this.authority = authority;
    this.expirationTime = expirationTime;
  }

  public String getToken() {
    return token;
  }

  public UUID getUserId() {
    return userId;
  }

  public Authority getAuthority() {
    return authority;
  }

  public LocalDateTime getExpirationTime() {
    return expirationTime;
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expirationTime);
  }
}
