package team.weero.app.application.port.in.answer;

import java.util.List;
import java.util.UUID;
import team.weero.app.application.port.in.answer.dto.response.GetAnswerInfo;

public interface GetAnswerUseCase {
  List<GetAnswerInfo> execute(UUID postId);
}
