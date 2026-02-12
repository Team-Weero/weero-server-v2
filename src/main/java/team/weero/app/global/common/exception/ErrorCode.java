package team.weero.app.global.common.exception;

import team.weero.app.global.error.dto.ErrorProperty;

public enum ErrorCode implements ErrorProperty {
  BAD_REQUEST(400, "Bad Request", 1),
  UNAUTHORIZED(401, "Unauthorized", 1),
  FORBIDDEN(403, "Forbidden", 1),
  NOT_FOUND(404, "Not Found", 1),
  CONFLICT(409, "Conflict", 1),
  UNPROCESSABLE_ENTITY(422, "Unprocessable Entity", 1),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error", 1);

  private final int status;
  private final String message;
  private final int sequence;

  ErrorCode(int status, String message, int sequence) {
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
    return "GLOBAL-" + status + "-" + sequence;
  }
}
