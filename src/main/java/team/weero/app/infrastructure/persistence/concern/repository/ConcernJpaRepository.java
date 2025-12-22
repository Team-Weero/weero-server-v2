package team.weero.app.infrastructure.persistence.concern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.infrastructure.persistence.concern.entity.ConcernJpaEntity;

import java.util.List;
import java.util.UUID;

public interface ConcernJpaRepository extends JpaRepository<ConcernJpaEntity, UUID> {
    List<ConcernJpaEntity> findByStudentIdOrderByCreatedAtDesc(UUID studentId);
    List<ConcernJpaEntity> findByIsResolvedOrderByCreatedAtDesc(boolean isResolved);
    List<ConcernJpaEntity> findAllByOrderByCreatedAtDesc();
}
