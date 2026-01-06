package team.weero.app.infrastructure.error.exception;

public class ExpiredJwtException extends WeeRoException {

  public static final WeeRoException EXCEPTION = new ExpiredJwtException();

  private ExpiredJwtException() {
    super(ErrorCode.EXPIRED_JWT);
  }
}
