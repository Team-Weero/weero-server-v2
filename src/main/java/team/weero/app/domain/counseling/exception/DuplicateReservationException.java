package team.weero.app.domain.counseling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateReservationException extends WeeRoException {

  public DuplicateReservationException() {
    super(ErrorCode.DUPLICATE_RESERVATION);
  }
}
