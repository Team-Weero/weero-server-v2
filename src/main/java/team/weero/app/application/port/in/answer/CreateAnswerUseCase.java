package team.weero.app.application.port.in.answer;

import java.util.UUID;
import team.weero.app.adapter.in.web.answer.dto.request.CreateAnswerRequest;

public interface CreateAnswerUseCase {

  void execute(CreateAnswerRequest request, UUID userId, UUID postId);
}
