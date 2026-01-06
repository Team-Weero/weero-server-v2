package team.weero.app.adapter.out.persistence.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

import java.util.Optional;
import java.util.UUID;

public interface TeacherJpaRepository extends JpaRepository<TeacherJpaEntity, UUID> {

    Optional<TeacherJpaEntity> findByAccountId(String accountId);
    Optional<TeacherJpaEntity> findByUser(UserJpaEntity user);
    boolean existsByAccountId(String accountId);
}
