package team.weero.app.core.answer.spi;

import team.weero.app.persistence.answer.entity.Answer;
import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.student.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnswerPort {
    Answer save(Answer answer);
    Optional<Answer> findById(UUID id);
    List<Answer> findByConcern(Concern concern);
    List<Answer> findByStudent(Student student);
    void deleteById(UUID id);
    int countByConcern(Concern concern);
}
