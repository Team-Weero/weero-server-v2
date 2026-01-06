package team.weero.app.application.port.out.answer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.answer.model.Answer;

public interface AnswerPort {
  Answer save(Answer answer);

  Optional<Answer> findById(UUID id);

  List<Answer> findByConcernId(UUID concernId);

  List<Answer> findByStudentId(UUID studentId);

  void deleteById(UUID id);

  int countByConcernId(UUID concernId);
}
