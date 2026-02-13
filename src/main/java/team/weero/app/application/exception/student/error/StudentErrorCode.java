package team.weero.app.application.exception.student.error;

import team.weero.app.global.error.dto.ErrorProperty;

public enum StudentErrorCode implements ErrorProperty {
  STUDENT_NOT_FOUND(404, "Student Not Found", 1);

  private final int status;
  private final String message;
  private final int sequence;

  StudentErrorCode(int status, String message, int sequence) {
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
    return "STUDENT-" + status + "-" + sequence;
  }
}
