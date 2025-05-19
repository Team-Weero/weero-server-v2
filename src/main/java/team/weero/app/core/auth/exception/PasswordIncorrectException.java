package team.weero.app.core.auth.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class PasswordIncorrectException extends CustomException{
    public static final CustomException EXCEPTION = new PasswordIncorrectException();

    public PasswordIncorrectException() {
        super(ErrorCode.PASSWORD_INCORRECT);
    }
}
