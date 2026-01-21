package team.weero.app.application.exception.answer.error;

import team.weero.app.global.error.ErrorProperty;

public enum AnswerErrorCode implements ErrorProperty {
  ANSWER_NOT_FOUND(404, "Answer Not Found", 1);

  private final int status;
  private final String message;
  private final int sequence;

  AnswerErrorCode(int status, String message, int sequence) {
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
    return "ANSWER-" + status + "-" + sequence;
  }
}
