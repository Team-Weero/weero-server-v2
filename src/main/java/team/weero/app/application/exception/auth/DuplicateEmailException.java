package team.weero.app.application.exception.auth;

import team.weero.app.application.exception.auth.error.AuthErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class DuplicateEmailException extends WeeRoException {

  public static final DuplicateEmailException INSTANCE = new DuplicateEmailException();

  private DuplicateEmailException() {
    super(AuthErrorCode.DUPLICATE_EMAIL);
  }
}
