package team.weero.app.application.exception.teacher;

import team.weero.app.application.exception.teacher.error.TeacherErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class TeacherNotFoundException extends WeeRoException {

  public static final TeacherNotFoundException INSTANCE = new TeacherNotFoundException();

  public TeacherNotFoundException() {
    super(TeacherErrorCode.TEACHER_NOT_FOUND);
  }
}
