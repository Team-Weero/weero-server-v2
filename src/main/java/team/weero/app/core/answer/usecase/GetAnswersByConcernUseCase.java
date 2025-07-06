package team.weero.app.core.answer.usecase;

import team.weero.app.core.Usecase;
import team.weero.app.core.answer.dto.response.AnswerResponse;
import team.weero.app.core.answer.service.AnswerService;

import java.util.List;
import java.util.UUID;

@Usecase
public class GetAnswersByConcernUseCase {

    private final AnswerService answerService;

    public GetAnswersByConcernUseCase(AnswerService answerService) {
        this.answerService = answerService;
    }

    public List<AnswerResponse> execute(UUID concernId) {
        return answerService.getAnswersByConcernId(concernId);
    }
}
