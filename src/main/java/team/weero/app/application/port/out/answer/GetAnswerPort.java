package team.weero.app.application.port.out.answer;

import java.util.UUID;
import team.weero.app.domain.answer.model.Answer;

public interface GetAnswerPort {
  Answer get(UUID postId);
}
