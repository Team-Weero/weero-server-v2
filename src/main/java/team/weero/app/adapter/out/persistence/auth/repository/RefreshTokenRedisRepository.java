package team.weero.app.adapter.out.persistence.auth.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import team.weero.app.adapter.out.persistence.auth.entity.RefreshTokenRedisEntity;

public interface RefreshTokenRedisRepository
    extends CrudRepository<RefreshTokenRedisEntity, String> {
  Optional<RefreshTokenRedisEntity> findByRefreshToken(String refreshToken);
}
