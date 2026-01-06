package team.weero.app.domain.counseling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends WeeRoException {

  public NotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }
}
