package team.weero.app.adapter.out.persistence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.domain.user.model.UserRole;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

import java.util.List;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {

    List<UserJpaEntity> findByUserRole(UserRole userRole);
}
