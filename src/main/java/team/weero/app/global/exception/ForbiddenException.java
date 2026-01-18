package team.weero.app.global.exception;

public class ForbiddenException extends BusinessException {

  public ForbiddenException() {
    super(NoticeErrorCode.FORBIDDEN_NOTICE_ACCESS);
  }
}
