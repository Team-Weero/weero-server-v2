package team.weero.app.core.counseling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends WeeRoException {

    public NotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
