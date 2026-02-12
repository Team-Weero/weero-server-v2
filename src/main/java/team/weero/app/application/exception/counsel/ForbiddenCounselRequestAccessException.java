package team.weero.app.application.exception.counsel;

import team.weero.app.application.exception.counsel.error.CounselRequestErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class ForbiddenCounselRequestAccessException extends WeeRoException {
  public ForbiddenCounselRequestAccessException() {
    super(CounselRequestErrorCode.FORBIDDEN_COUNSEL_REQUEST_ACCESS);
  }
}
