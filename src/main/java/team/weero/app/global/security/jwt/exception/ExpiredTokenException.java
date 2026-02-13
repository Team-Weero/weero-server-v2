package team.weero.app.global.security.jwt.exception;

import team.weero.app.application.exception.auth.error.AuthErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class ExpiredTokenException extends WeeRoException {

  public static final ExpiredTokenException INSTANCE = new ExpiredTokenException();

  private ExpiredTokenException() {
    super(AuthErrorCode.EXPIRED_TOKEN);
  }
}
