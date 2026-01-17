package team.weero.app.global.exception;

public class UserNotFoundException extends BusinessException {

  public static final UserNotFoundException INSTANCE = new UserNotFoundException();

  private UserNotFoundException() {
    super(AuthErrorCode.USER_NOT_FOUND);
  }
}
