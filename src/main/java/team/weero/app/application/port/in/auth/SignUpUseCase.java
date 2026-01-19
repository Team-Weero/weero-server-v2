package team.weero.app.application.port.in.auth;

import team.weero.app.adapter.in.web.auth.dto.request.SignUpCommand;

public interface SignUpUseCase {
  void execute(SignUpCommand command);
}
