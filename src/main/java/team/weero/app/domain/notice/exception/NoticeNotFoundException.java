package team.weero.app.domain.notice.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

public class NoticeNotFoundException extends WeeRoException {
  public static final WeeRoException EXCEPTION = new NoticeNotFoundException();

  private NoticeNotFoundException() {
    super(ErrorCode.NOTICE_NOT_FOUND);
  }
}
