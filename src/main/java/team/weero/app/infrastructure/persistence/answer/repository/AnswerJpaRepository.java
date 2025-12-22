package team.weero.app.infrastructure.persistence.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.infrastructure.persistence.answer.entity.AnswerJpaEntity;
import team.weero.app.infrastructure.persistence.student.entity.StudentJpaEntity;
import team.weero.app.infrastructure.persistence.concern.entity.ConcernJpaEntity;

import java.util.List;
import java.util.UUID;

public interface AnswerJpaRepository extends JpaRepository<AnswerJpaEntity, UUID> {
    List<AnswerJpaEntity> findByConcernOrderByCreatedAtAsc(ConcernJpaEntity concern);
    List<AnswerJpaEntity> findByStudentOrderByCreatedAtDesc(StudentJpaEntity student);
    int countByConcern(ConcernJpaEntity concern);
}
