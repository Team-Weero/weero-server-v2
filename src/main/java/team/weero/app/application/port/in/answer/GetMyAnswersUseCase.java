package team.weero.app.application.port.in.answer;

import team.weero.app.application.service.answer.dto.response.AnswerResponse;

import java.util.List;

public interface GetMyAnswersUseCase {
    List<AnswerResponse> execute(String accountId);
}
