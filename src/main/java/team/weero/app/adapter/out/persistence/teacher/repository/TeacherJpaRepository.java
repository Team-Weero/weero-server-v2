package team.weero.app.adapter.out.persistence.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Teacher JPA Repository
 * Spring Data JPA 인터페이스
 */
public interface TeacherJpaRepository extends JpaRepository<TeacherJpaEntity, UUID> {

    /**
     * accountId로 조회
     */
    Optional<TeacherJpaEntity> findByAccountId(String accountId);

    /**
     * User로 조회
     */
    Optional<TeacherJpaEntity> findByUser(UserJpaEntity user);

    /**
     * accountId 존재 여부 확인
     */
    boolean existsByAccountId(String accountId);
}
