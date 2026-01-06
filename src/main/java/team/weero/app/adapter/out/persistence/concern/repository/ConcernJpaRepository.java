package team.weero.app.adapter.out.persistence.concern.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.adapter.out.persistence.concern.entity.ConcernJpaEntity;

public interface ConcernJpaRepository extends JpaRepository<ConcernJpaEntity, UUID> {
  List<ConcernJpaEntity> findByStudentIdOrderByCreatedAtDesc(UUID studentId);

  List<ConcernJpaEntity> findByIsResolvedOrderByCreatedAtDesc(boolean isResolved);

  List<ConcernJpaEntity> findAllByOrderByCreatedAtDesc();
}
