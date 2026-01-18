package team.weero.app.global.exception;

public class InvalidTokenException extends BusinessException {

  public static final InvalidTokenException INSTANCE = new InvalidTokenException();

  private InvalidTokenException() {
    super(AuthErrorCode.INVALID_TOKEN);
  }
}
