package team.weero.app.infrastructure.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class InvalidJwtException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
