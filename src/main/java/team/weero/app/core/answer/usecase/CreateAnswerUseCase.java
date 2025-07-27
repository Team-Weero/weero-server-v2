package team.weero.app.core.answer.usecase;

import team.weero.app.core.Usecase;
import team.weero.app.core.answer.dto.request.CreateAnswerRequest;
import team.weero.app.core.answer.service.AnswerService;

@Usecase
public class CreateAnswerUseCase {

    private final AnswerService answerService;

    public CreateAnswerUseCase(AnswerService answerService) {
        this.answerService = answerService;
    }

    public void execute(CreateAnswerRequest request, String accountId) {
        answerService.createAnswer(request, accountId);
    }
}
