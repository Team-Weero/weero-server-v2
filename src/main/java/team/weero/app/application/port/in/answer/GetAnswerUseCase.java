package team.weero.app.application.port.in.answer;

import java.util.UUID;
import team.weero.app.adapter.in.web.answer.dto.response.GetAnswerResponse;

public interface GetAnswerUseCase {

  GetAnswerResponse execute(UUID postId);
}
