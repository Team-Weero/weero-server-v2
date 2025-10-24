package team.weero.app.persistence.concern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.student.entity.Student;

import java.util.List;
import java.util.UUID;

public interface ConcernRepository extends JpaRepository<Concern, UUID> {
    List<Concern> findByStudentOrderByCreatedAtDesc(Student student);
    List<Concern> findByIsResolvedOrderByCreatedAtDesc(boolean isResolved);
    List<Concern> findAllByOrderByCreatedAtDesc();
}
