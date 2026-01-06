package team.weero.app.infrastructure.error.exception;

public class InvalidJwtException extends WeeRoException {

  public static final WeeRoException EXCEPTION = new InvalidJwtException();

  private InvalidJwtException() {
    super(ErrorCode.INVALID_JWT);
  }
}
