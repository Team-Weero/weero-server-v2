package team.weero.app.core.auth.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends WeeRoException {

    public static final WeeRoException EXCEPTION = new RefreshTokenNotFoundException();

    public RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
