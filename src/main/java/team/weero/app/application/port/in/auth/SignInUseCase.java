package team.weero.app.application.port.in.auth;

import team.weero.app.application.port.in.auth.dto.request.SignInCommand;
import team.weero.app.application.port.in.auth.dto.response.SignInInfo;

public interface SignInUseCase {

  SignInInfo execute(SignInCommand command);
}
