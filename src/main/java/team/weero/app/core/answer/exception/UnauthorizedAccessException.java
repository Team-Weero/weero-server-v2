package team.weero.app.core.answer.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class UnauthorizedAccessException extends CustomException {
    public static final CustomException EXCEPTION = new UnauthorizedAccessException();

    private UnauthorizedAccessException() {
        super(ErrorCode.UNAUTHORIZED_ACCESS);
    }
}
