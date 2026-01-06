package team.weero.app.domain.auth.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.WeeRoException;

public class InvalidRefreshTokenException extends WeeRoException {

  public static final WeeRoException EXCEPTION = new InvalidRefreshTokenException();

  public InvalidRefreshTokenException() {
    super(ErrorCode.INVALID_REFRESH_TOKEN);
  }
}
