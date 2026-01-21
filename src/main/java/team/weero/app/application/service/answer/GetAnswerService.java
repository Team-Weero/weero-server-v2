package team.weero.app.application.service.answer;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.answer.dto.response.GetAnswerResponse;
import team.weero.app.application.port.in.answer.GetAnswerUseCase;
import team.weero.app.application.port.out.answer.GetAnswerPort;
import team.weero.app.domain.answer.model.Answer;

@Service
@RequiredArgsConstructor
public class GetAnswerService implements GetAnswerUseCase {

  private final GetAnswerPort getAnswerPort;

  @Transactional
  @Override
  public GetAnswerResponse execute(UUID postId) {

    Answer answer = getAnswerPort.get(postId);

    return GetAnswerResponse.from(answer);
  }
}
