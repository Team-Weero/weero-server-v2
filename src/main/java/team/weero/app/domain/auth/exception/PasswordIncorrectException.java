package team.weero.app.domain.auth.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

public class PasswordIncorrectException extends WeeRoException {
  public static final WeeRoException EXCEPTION = new PasswordIncorrectException();

  public PasswordIncorrectException() {
    super(ErrorCode.PASSWORD_INCORRECT);
  }
}
