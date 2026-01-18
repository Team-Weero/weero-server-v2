package team.weero.app.global.exception;

public enum NoticeErrorCode implements ErrorProperty {
  NOTICE_NOT_FOUND(404, "Notice Not Found", 1),
  FORBIDDEN_NOTICE_ACCESS(403, "Forbidden Notice Access", 1);

  private final int status;
  private final String message;
  private final int sequence;

  NoticeErrorCode(int status, String message, int sequence) {
    this.status = status;
    this.message = message;
    this.sequence = sequence;
  }

  @Override
  public int status() {
    return status;
  }

  @Override
  public String message() {
    return message;
  }

  @Override
  public String code() {
    return "NOTICE-" + status + "-" + sequence;
  }
}
