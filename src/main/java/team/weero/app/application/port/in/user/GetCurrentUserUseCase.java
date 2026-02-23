package team.weero.app.application.port.in.user;

import team.weero.app.application.port.in.user.dto.response.CurrentUserInfo;

public interface GetCurrentUserUseCase {

  CurrentUserInfo execute();
}
