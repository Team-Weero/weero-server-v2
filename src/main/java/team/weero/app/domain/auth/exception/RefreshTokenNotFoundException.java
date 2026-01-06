package team.weero.app.domain.auth.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

public class RefreshTokenNotFoundException extends WeeRoException {

  public static final WeeRoException EXCEPTION = new RefreshTokenNotFoundException();

  public RefreshTokenNotFoundException() {
    super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
  }
}
