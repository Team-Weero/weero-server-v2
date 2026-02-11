package team.weero.app.application.service.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.answer.CreateAnswerUseCase;
import team.weero.app.application.port.in.answer.dto.request.CreateAnswerCommand;
import team.weero.app.application.port.out.answer.CreateAnswerPort;
import team.weero.app.domain.answer.model.Answer;

@Service
@RequiredArgsConstructor
public class CreateAnswerService implements CreateAnswerUseCase {

  private final CreateAnswerPort createAnswerPort;

  @Override
  @Transactional
  public void execute(CreateAnswerCommand command) {
    Answer answer = Answer.create(command.answer(), command.userId(), command.postId());
    createAnswerPort.save(answer);
  }
}
