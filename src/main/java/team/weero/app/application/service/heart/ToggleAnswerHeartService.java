package team.weero.app.application.service.heart;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.heart.ToggleAnswerHeartUseCase;
import team.weero.app.application.port.out.heart.AnswerHeartPort;

@Service
@RequiredArgsConstructor
public class ToggleAnswerHeartService implements ToggleAnswerHeartUseCase {

  private final AnswerHeartPort answerHeartPort;

  @Override
  @Transactional
  public void execute(UUID answerId, UUID userId) {
    if (answerHeartPort.exists(answerId, userId)) {
      answerHeartPort.delete(answerId, userId);
    } else {
      answerHeartPort.save(answerId, userId);
    }
  }
}
