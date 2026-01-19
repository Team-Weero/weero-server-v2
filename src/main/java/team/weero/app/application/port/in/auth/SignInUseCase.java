package team.weero.app.application.port.in.auth;

import team.weero.app.adapter.in.web.auth.dto.request.SignInCommand;
import team.weero.app.adapter.in.web.auth.dto.response.SignInResponse;

public interface SignInUseCase {

  SignInResponse execute(SignInCommand command);
}
