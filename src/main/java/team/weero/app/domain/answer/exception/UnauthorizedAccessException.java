package team.weero.app.domain.answer.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

public class UnauthorizedAccessException extends WeeRoException {
  public static final WeeRoException EXCEPTION = new UnauthorizedAccessException();

  private UnauthorizedAccessException() {
    super(ErrorCode.UNAUTHORIZED_ACCESS);
  }
}
