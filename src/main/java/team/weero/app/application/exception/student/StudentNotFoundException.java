package team.weero.app.application.exception.student;

import team.weero.app.application.exception.student.error.StudentErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class StudentNotFoundException extends WeeRoException {

  public static final StudentNotFoundException INSTANCE = new StudentNotFoundException();

  public StudentNotFoundException() {
    super(StudentErrorCode.STUDENT_NOT_FOUND);
  }
}
