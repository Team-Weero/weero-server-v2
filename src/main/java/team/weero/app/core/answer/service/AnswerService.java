package team.weero.app.core.answer.service;

import team.weero.app.core.answer.dto.request.CreateAnswerRequest;
import team.weero.app.core.answer.dto.response.AnswerResponse;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    void createAnswer(CreateAnswerRequest request, String accountId);
    List<AnswerResponse> getAnswersByConcernId(UUID concernId);
    List<AnswerResponse> getMyAnswers(String accountId);
    void deleteAnswer(UUID answerId, String accountId);
}
