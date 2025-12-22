package team.weero.app.infrastructure.persistence.auth.repository;

import org.springframework.data.repository.CrudRepository;
import team.weero.app.infrastructure.persistence.auth.entity.RefreshTokenRedisEntity;

import java.util.Optional;

/**
 * RefreshToken Redis Repository
 * Spring Data Redis repository interface
 */
public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenRedisEntity, String> {
    Optional<RefreshTokenRedisEntity> findByRefreshToken(String refreshToken);
}
