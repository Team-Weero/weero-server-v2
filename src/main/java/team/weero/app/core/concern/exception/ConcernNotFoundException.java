package team.weero.app.core.concern.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class ConcernNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new ConcernNotFoundException();

    private ConcernNotFoundException() {
        super(ErrorCode.CONCERN_NOT_FOUND);
    }
}
