package team.weero.app.global.security.jwt.exception;

import team.weero.app.application.exception.auth.error.AuthErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class InvalidTokenException extends WeeRoException {

  public static final InvalidTokenException INSTANCE = new InvalidTokenException();

  private InvalidTokenException() {
    super(AuthErrorCode.INVALID_TOKEN);
  }
}
