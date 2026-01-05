package team.weero.app.application.port.in.answer;

import team.weero.app.application.service.answer.dto.request.CreateAnswerRequest;

public interface CreateAnswerUseCase {
    void execute(CreateAnswerRequest request, String accountId);
}
