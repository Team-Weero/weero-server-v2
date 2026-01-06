package team.weero.app.domain.auth.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.WeeRoException;

public class UserNotFoundException extends WeeRoException {

  public static final WeeRoException EXCEPTION = new UserNotFoundException();

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }
}
