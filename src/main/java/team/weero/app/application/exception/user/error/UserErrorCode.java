package team.weero.app.application.exception.user.error;

import team.weero.app.global.error.dto.ErrorProperty;

public enum UserErrorCode implements ErrorProperty {
  USER_NOT_FOUND(404, "User Not Found", 1);

  private final int status;
  private final String message;
  private final int sequence;

  UserErrorCode(int status, String message, int sequence) {
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
    return "USER-" + status + "-" + sequence;
  }
}
