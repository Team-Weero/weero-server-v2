package team.weero.app.application.service.answer;

import java.util.List;
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

  @Transactional(readOnly = true)
  @Override
  public GetAnswerResponse execute(UUID postId) {

    List<Answer> answers = getAnswerPort.getAll(postId);

    return GetAnswerResponse.from(answers);
  }
}
