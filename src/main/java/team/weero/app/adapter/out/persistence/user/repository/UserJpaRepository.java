package team.weero.app.adapter.out.persistence.user.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.domain.user.model.UserRole;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {

  List<UserJpaEntity> findByUserRole(UserRole userRole);
}
