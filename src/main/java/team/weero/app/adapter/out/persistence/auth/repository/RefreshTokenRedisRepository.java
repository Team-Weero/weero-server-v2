package team.weero.app.adapter.out.persistence.auth.repository;

import org.springframework.data.repository.CrudRepository;
import team.weero.app.adapter.out.persistence.auth.entity.RefreshTokenRedisEntity;

import java.util.Optional;

/**
 * RefreshToken Redis Repository
 * Spring Data Redis repository interface
 */
public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenRedisEntity, String> {
    Optional<RefreshTokenRedisEntity> findByRefreshToken(String refreshToken);
}
