package team.weero.app.core.teacher.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class TeacherNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}
