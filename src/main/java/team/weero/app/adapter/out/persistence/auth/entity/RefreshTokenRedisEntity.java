package team.weero.app.adapter.out.persistence.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenRedisEntity {
    @Id
    private String accountId;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;
}
