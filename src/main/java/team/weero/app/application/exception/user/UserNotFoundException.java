package team.weero.app.application.exception.user;

import team.weero.app.application.exception.user.error.UserErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class UserNotFoundException extends WeeRoException {

  public static final UserNotFoundException INSTANCE = new UserNotFoundException();

  private UserNotFoundException() {
    super(UserErrorCode.USER_NOT_FOUND);
  }
}
