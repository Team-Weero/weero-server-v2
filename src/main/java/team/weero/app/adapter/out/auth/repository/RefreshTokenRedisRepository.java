package team.weero.app.adapter.out.auth.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.auth.entity.RefreshTokenJpaEntity;

@Repository
public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenJpaEntity, UUID> {

  Optional<RefreshTokenJpaEntity> findByToken(String token);

  void deleteByToken(String token);
}
