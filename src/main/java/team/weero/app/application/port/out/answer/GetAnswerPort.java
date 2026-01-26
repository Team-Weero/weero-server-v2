package team.weero.app.application.port.out.answer;

import java.util.List;
import java.util.UUID;
import team.weero.app.domain.answer.model.Answer;

public interface GetAnswerPort {
  List<Answer> getAll(UUID postId);
}
