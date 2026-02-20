package team.weero.app.application.exception.user;

import team.weero.app.application.exception.user.error.UserErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class UserNotFoundException extends WeeRoException {

  public static final UserNotFoundException INSTANCE = new UserNotFoundException();

  public UserNotFoundException() {
    super(UserErrorCode.USER_NOT_FOUND);
  }
}
