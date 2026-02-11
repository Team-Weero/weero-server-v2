package team.weero.app.application.port.in.answer;

import team.weero.app.application.port.in.answer.dto.request.CreateAnswerCommand;

public interface CreateAnswerUseCase {

  void execute(CreateAnswerCommand command);
}
