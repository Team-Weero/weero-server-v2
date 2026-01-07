package team.weero.app.adapter.out.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserJpaEntity, UUID> {
}
