package team.weero.app.application.service.answer;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.answer.dto.request.CreateAnswerRequest;
import team.weero.app.application.port.in.answer.CreateAnswerUseCase;
import team.weero.app.application.port.out.answer.CreateAnswerPort;

@Service
@RequiredArgsConstructor
public class CreateAnswerService implements CreateAnswerUseCase {

  private final CreateAnswerPort createAnswerPort;

  @Override
  @Transactional
  public void execute(CreateAnswerRequest request, UUID userId, UUID postId) {

    createAnswerPort.save(request.answer(), userId, postId);
  }
}
