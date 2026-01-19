package team.weero.app.application.exception.auth;

import team.weero.app.application.exception.auth.error.AuthErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class UserNotFoundException extends WeeRoException {

  public static final UserNotFoundException INSTANCE = new UserNotFoundException();

  private UserNotFoundException() {
    super(AuthErrorCode.USER_NOT_FOUND);
  }
}
