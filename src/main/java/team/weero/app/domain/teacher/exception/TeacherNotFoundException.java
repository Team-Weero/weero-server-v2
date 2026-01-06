package team.weero.app.domain.teacher.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.WeeRoException;


public class TeacherNotFoundException extends WeeRoException {

    public static final WeeRoException EXCEPTION = new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}
