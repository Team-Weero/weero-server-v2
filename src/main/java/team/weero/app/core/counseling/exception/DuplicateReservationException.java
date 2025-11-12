package team.weero.app.core.counseling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateReservationException extends WeeRoException {

  public DuplicateReservationException() {
    super(ErrorCode.DUPLICATE_RESERVATION);
  }
}
