package team.weero.app.domain.notice.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

public class UnauthorizedNoticeAccessException extends WeeRoException {
  public static final WeeRoException EXCEPTION = new UnauthorizedNoticeAccessException();

  private UnauthorizedNoticeAccessException() {
    super(ErrorCode.UNAUTHORIZED_NOTICE_ACCESS);
  }
}
