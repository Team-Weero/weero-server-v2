package team.weero.app.application.port.in.answer;

import java.util.List;
import java.util.UUID;
import team.weero.app.application.service.answer.dto.response.AnswerResponse;

public interface GetAnswersByConcernUseCase {
  List<AnswerResponse> execute(UUID concernId);
}
