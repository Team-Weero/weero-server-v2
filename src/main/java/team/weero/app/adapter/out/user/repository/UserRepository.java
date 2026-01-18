package team.weero.app.adapter.out.user.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;

@Repository
public interface UserRepository extends CrudRepository<UserJpaEntity, UUID> {

  Optional<UserJpaEntity> findByEmail(String email);
}
