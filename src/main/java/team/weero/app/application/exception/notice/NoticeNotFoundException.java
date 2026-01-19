package team.weero.app.application.exception.notice;

import team.weero.app.application.exception.notice.error.NoticeErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class NoticeNotFoundException extends WeeRoException {

  public NoticeNotFoundException() {
    super(NoticeErrorCode.NOTICE_NOT_FOUND);
  }
}
