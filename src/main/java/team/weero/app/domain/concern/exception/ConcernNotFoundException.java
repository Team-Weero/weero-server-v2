package team.weero.app.domain.concern.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.WeeRoException;

public class ConcernNotFoundException extends WeeRoException {
  public static final WeeRoException EXCEPTION = new ConcernNotFoundException();

  private ConcernNotFoundException() {
    super(ErrorCode.CONCERN_NOT_FOUND);
  }
}
