package team.weero.app.domain.teacher.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.WeeRoException;

/**
 * Teacher를 찾을 수 없을 때 발생하는 예외
 */
public class TeacherNotFoundException extends WeeRoException {

    public static final WeeRoException EXCEPTION = new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}
