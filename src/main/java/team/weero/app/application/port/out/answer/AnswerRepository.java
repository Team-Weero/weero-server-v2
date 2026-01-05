package team.weero.app.application.port.out.answer;

import team.weero.app.domain.answer.model.Answer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnswerRepository {
    Answer save(Answer answer);
    Optional<Answer> findById(UUID id);
    List<Answer> findByConcernId(UUID concernId);
    List<Answer> findByStudentId(UUID studentId);
    void deleteById(UUID id);
    int countByConcernId(UUID concernId);
}
