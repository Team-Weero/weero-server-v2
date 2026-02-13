package team.weero.app.global.common.exception;

import team.weero.app.application.exception.notice.error.NoticeErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class ForbiddenException extends WeeRoException {

  public ForbiddenException() {
    super(NoticeErrorCode.FORBIDDEN_NOTICE_ACCESS);
  }
}
