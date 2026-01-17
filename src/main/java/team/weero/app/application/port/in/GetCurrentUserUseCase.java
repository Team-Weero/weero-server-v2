package team.weero.app.application.port.in;

import team.weero.app.domain.auth.AuthUser;

public interface GetCurrentUserUseCase {

  AuthUser execute();
}
