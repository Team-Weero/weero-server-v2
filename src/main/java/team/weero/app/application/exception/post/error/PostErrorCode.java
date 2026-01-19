package team.weero.app.application.exception.post.error;

import team.weero.app.global.error.ErrorProperty;

public enum PostErrorCode implements ErrorProperty {
  POST_NOT_FOUND(404, "Post Not Found", 1),
  FORBIDDEN_POST_ACCESS(403, "Forbidden Post Access", 1);

  private final int status;
  private final String message;
  private final int sequence;

  PostErrorCode(int status, String message, int sequence) {
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
    return "POST-" + status + "-" + sequence;
  }
}
