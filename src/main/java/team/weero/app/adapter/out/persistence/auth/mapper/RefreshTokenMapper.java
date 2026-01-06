package team.weero.app.adapter.out.persistence.auth.mapper;

import team.weero.app.domain.auth.model.RefreshToken;
import team.weero.app.adapter.out.persistence.auth.entity.RefreshTokenRedisEntity;

public class RefreshTokenMapper {

    public static RefreshTokenRedisEntity toEntity(RefreshToken domain) {
        if (domain == null) {
            return null;
        }

        return RefreshTokenRedisEntity.builder()
                .accountId(domain.getAccountId())
                .refreshToken(domain.getRefreshToken())
                .ttl(domain.getTtl())
                .build();
    }

    public static RefreshToken toDomain(RefreshTokenRedisEntity entity) {
        if (entity == null) {
            return null;
        }

        return RefreshToken.builder()
                .accountId(entity.getAccountId())
                .refreshToken(entity.getRefreshToken())
                .ttl(entity.getTtl())
                .build();
    }
}
