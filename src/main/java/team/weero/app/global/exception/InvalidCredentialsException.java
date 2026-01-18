package team.weero.app.global.exception;

public class InvalidCredentialsException extends BusinessException {

  public static final InvalidCredentialsException INSTANCE = new InvalidCredentialsException();

  private InvalidCredentialsException() {
    super(AuthErrorCode.INVALID_CREDENTIALS);
  }
}
