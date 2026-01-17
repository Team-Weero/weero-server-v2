package team.weero.app.global.exception;

public class TeacherSignUpNotAllowedException extends BusinessException {

  public static final TeacherSignUpNotAllowedException INSTANCE =
      new TeacherSignUpNotAllowedException();

  private TeacherSignUpNotAllowedException() {
    super(AuthErrorCode.TEACHER_SIGNUP_NOT_ALLOWED);
  }
}
