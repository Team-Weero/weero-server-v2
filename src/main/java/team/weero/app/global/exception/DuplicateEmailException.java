package team.weero.app.global.exception;

public class DuplicateEmailException extends BusinessException {

  public static final DuplicateEmailException INSTANCE = new DuplicateEmailException();

  private DuplicateEmailException() {
    super(AuthErrorCode.DUPLICATE_EMAIL);
  }
}
