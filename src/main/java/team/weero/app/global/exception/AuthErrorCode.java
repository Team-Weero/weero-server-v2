package team.weero.app.global.exception;

public enum AuthErrorCode implements ErrorProperty {
  INVALID_CREDENTIALS(401, "Invalid Credentials", 1),
  EXPIRED_TOKEN(401, "Expired Token", 2),
  INVALID_TOKEN(401, "Invalid Token", 3),
  TEACHER_SIGNUP_NOT_ALLOWED(403, "Teacher Sign Up Not Allowed", 1),
  USER_NOT_FOUND(404, "User Not Found", 1),
  DUPLICATE_EMAIL(409, "Duplicate Email", 1);

  private final int status;
  private final String message;
  private final int sequence;

  AuthErrorCode(int status, String message, int sequence) {
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
    return "AUTH-" + status + "-" + sequence;
  }
}
