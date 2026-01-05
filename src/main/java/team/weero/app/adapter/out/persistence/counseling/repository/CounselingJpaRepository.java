package team.weero.app.infrastructure.persistence.counseling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.infrastructure.persistence.counseling.entity.CounselingApplicationJpaEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CounselingJpaRepository extends JpaRepository<CounselingApplicationJpaEntity, UUID> {

    List<CounselingApplicationJpaEntity> findByStudentId(UUID studentId);

    List<CounselingApplicationJpaEntity> findByTeacherId(UUID teacherId);

    boolean existsByTeacherIdAndCounselDateAndTime(UUID teacherId, LocalDate counselDate, LocalTime time);
}
