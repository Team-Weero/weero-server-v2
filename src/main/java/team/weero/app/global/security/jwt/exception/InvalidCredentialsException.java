package team.weero.app.global.security.jwt.exception;

import team.weero.app.application.exception.auth.error.AuthErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class InvalidCredentialsException extends WeeRoException {

  public static final InvalidCredentialsException INSTANCE = new InvalidCredentialsException();

  private InvalidCredentialsException() {
    super(AuthErrorCode.INVALID_CREDENTIALS);
  }
}
