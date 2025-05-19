package team.weero.app.core.auth.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
