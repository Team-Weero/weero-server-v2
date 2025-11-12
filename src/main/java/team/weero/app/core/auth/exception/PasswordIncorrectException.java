package team.weero.app.core.auth.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class PasswordIncorrectException extends WeeRoException {
    public static final WeeRoException EXCEPTION = new PasswordIncorrectException();

    public PasswordIncorrectException() {
        super(ErrorCode.PASSWORD_INCORRECT);
    }
}
