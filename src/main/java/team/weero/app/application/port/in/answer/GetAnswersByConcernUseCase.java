package team.weero.app.application.port.in.answer;

import team.weero.app.application.service.answer.dto.response.AnswerResponse;

import java.util.List;
import java.util.UUID;

public interface GetAnswersByConcernUseCase {
    List<AnswerResponse> execute(UUID concernId);
}
