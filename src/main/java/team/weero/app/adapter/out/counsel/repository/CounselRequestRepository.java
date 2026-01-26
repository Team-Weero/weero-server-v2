package team.weero.app.adapter.out.counsel.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.domain.counsel.type.Status;

@Repository
public interface CounselRequestRepository extends JpaRepository<CounselRequestJpaEntity, UUID> {

    Optional<CounselRequestJpaEntity> findByIdAndDeletedTimeIsNull(UUID id);

    List<CounselRequestJpaEntity> findAllByDeletedTimeIsNullOrderByCreatedAtDesc();

    List<CounselRequestJpaEntity> findAllByStudentIdAndDeletedTimeIsNullOrderByCreatedAtDesc(UUID studentId);

    List<CounselRequestJpaEntity> findAllByTeacherIdAndDeletedTimeIsNullOrderByCreatedAtDesc(UUID teacherId);

    List<CounselRequestJpaEntity> findAllByStatusAndDeletedTimeIsNullAndUpdatedAtBefore(
            Status status, LocalDateTime updatedAt);
}
