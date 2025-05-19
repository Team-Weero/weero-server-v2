package team.weero.app.core.auth.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class UserAlreadyExistsException extends CustomException {

    public static final CustomException EXCEPTION = new UserAlreadyExistsException();

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
