package team.weero.app.application.exception.teacher;

import team.weero.app.application.exception.teacher.error.TeacherErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class TeacherNotFoundException extends WeeRoException {
    public TeacherNotFoundException() {
        super(TeacherErrorCode.TEACHER_NOT_FOUND);
    }
}
