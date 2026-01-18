package team.weero.app.adapter.out.auth.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import team.weero.app.domain.auth.RefreshToken;
import team.weero.app.domain.auth.type.Authority;

@RedisHash("tbl_refresh_token")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenJpaEntity {

  @Id private UUID userId;

  @Indexed private String token;

  private Authority authority;

  @TimeToLive private Long expirationTime;

  public static RefreshTokenJpaEntity from(RefreshToken domain) {
    long ttl =
        domain.getExpirationTime().atZone(ZoneId.systemDefault()).toEpochSecond()
            - LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();

    if (ttl <= 0) {
      throw new IllegalArgumentException("Refresh token has already expired");
    }

    return RefreshTokenJpaEntity.builder()
        .userId(domain.getUserId())
        .token(domain.getToken())
        .authority(domain.getAuthority())
        .expirationTime(ttl)
        .build();
  }

  public RefreshToken toDomain() {
    LocalDateTime expirationDateTime =
        LocalDateTime.now().plusSeconds(this.expirationTime != null ? this.expirationTime : 0);

    return new RefreshToken(this.token, this.userId, this.authority, expirationDateTime);
  }
}
