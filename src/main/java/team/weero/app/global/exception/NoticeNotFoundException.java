package team.weero.app.global.exception;

public class NoticeNotFoundException extends BusinessException {

  public NoticeNotFoundException() {
    super(NoticeErrorCode.NOTICE_NOT_FOUND);
  }
}
