package team.weero.app.infrastructure.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class ExpiredJwtException extends CustomException {

    public static final CustomException EXCEPTION = new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
