package team.weero.app.application.service.answer;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.application.port.in.answer.DeleteAnswerUseCase;
import team.weero.app.application.port.out.answer.DeleteAnswerPort;

@Service
@RequiredArgsConstructor
public class DeleteAnswerService implements DeleteAnswerUseCase {

  private final DeleteAnswerPort deleteAnswerPort;

  public void execute(UUID answerId) {
    deleteAnswerPort.delete(answerId);
  }
}
