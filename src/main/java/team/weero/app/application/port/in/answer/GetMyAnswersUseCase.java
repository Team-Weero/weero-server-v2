package team.weero.app.application.port.in.answer;

import java.util.List;
import team.weero.app.application.service.answer.dto.response.AnswerResponse;

public interface GetMyAnswersUseCase {
  List<AnswerResponse> execute(String accountId);
}
