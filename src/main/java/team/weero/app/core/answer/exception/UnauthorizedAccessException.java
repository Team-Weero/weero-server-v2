package team.weero.app.core.answer.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class UnauthorizedAccessException extends WeeRoException {
    public static final WeeRoException EXCEPTION = new UnauthorizedAccessException();

    private UnauthorizedAccessException() {
        super(ErrorCode.UNAUTHORIZED_ACCESS);
    }
}
