package team.weero.app.application.port.in.user;

import team.weero.app.application.port.in.user.dto.response.UserInfo;

public interface GetCurrentUserUseCase {

  UserInfo execute();
}
