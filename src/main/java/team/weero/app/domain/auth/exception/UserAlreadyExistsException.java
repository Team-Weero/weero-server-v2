package team.weero.app.domain.auth.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class UserAlreadyExistsException extends WeeRoException {

    public static final WeeRoException EXCEPTION = new UserAlreadyExistsException();

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
