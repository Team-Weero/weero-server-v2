package team.weero.app.application.exception.auth;

import team.weero.app.application.exception.auth.error.AuthErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class TeacherSignUpNotAllowedException extends WeeRoException {

  public static final TeacherSignUpNotAllowedException INSTANCE =
      new TeacherSignUpNotAllowedException();

  private TeacherSignUpNotAllowedException() {
    super(AuthErrorCode.TEACHER_SIGNUP_NOT_ALLOWED);
  }
}
