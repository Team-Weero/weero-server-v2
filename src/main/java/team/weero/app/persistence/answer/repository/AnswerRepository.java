package team.weero.app.persistence.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.persistence.answer.entity.Answer;
import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.student.entity.Student;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findByConcernOrderByCreatedAtAsc(Concern concern);
    List<Answer> findByStudentOrderByCreatedAtDesc(Student student);
    int countByConcern(Concern concern);
}
