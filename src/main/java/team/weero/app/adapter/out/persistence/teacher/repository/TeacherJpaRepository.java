package team.weero.app.adapter.out.persistence.teacher.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

public interface TeacherJpaRepository extends JpaRepository<TeacherJpaEntity, UUID> {

  Optional<TeacherJpaEntity> findByAccountId(String accountId);

  Optional<TeacherJpaEntity> findByUser(UserJpaEntity user);

  boolean existsByAccountId(String accountId);
}
