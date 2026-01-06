package team.weero.app.adapter.out.persistence.answer.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.adapter.out.persistence.answer.entity.AnswerJpaEntity;
import team.weero.app.adapter.out.persistence.concern.entity.ConcernJpaEntity;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;

public interface AnswerJpaRepository extends JpaRepository<AnswerJpaEntity, UUID> {
  List<AnswerJpaEntity> findByConcernOrderByCreatedAtAsc(ConcernJpaEntity concern);

  List<AnswerJpaEntity> findByStudentOrderByCreatedAtDesc(StudentJpaEntity student);

  int countByConcern(ConcernJpaEntity concern);
}
