package team.weero.app.global.exception;

public class ErrorResponse {

  private final int status;
  private final String message;
  private final String code;

  private ErrorResponse(int status, String message, String code) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  public static ErrorResponse of(ErrorProperty errorProperty) {
    return new ErrorResponse(errorProperty.status(), errorProperty.message(), errorProperty.code());
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }
}
