package team.weero.app.domain.auth.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class InvalidRefreshTokenException extends WeeRoException {

    public static final WeeRoException EXCEPTION = new InvalidRefreshTokenException();

    public InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
