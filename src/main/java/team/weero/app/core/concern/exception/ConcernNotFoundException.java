package team.weero.app.core.concern.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class ConcernNotFoundException extends WeeRoException {
    public static final WeeRoException EXCEPTION = new ConcernNotFoundException();

    private ConcernNotFoundException() {
        super(ErrorCode.CONCERN_NOT_FOUND);
    }
}
