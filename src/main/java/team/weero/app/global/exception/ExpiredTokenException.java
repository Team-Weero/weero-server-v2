package team.weero.app.global.exception;

public class ExpiredTokenException extends BusinessException {

  public static final ExpiredTokenException INSTANCE = new ExpiredTokenException();

  private ExpiredTokenException() {
    super(AuthErrorCode.EXPIRED_TOKEN);
  }
}
