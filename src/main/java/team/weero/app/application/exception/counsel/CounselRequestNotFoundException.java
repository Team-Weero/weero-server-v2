package team.weero.app.application.exception.counsel;

import team.weero.app.application.exception.counsel.error.CounselRequestErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class CounselRequestNotFoundException extends WeeRoException {
  public CounselRequestNotFoundException() {
    super(CounselRequestErrorCode.COUNSEL_REQUEST_NOT_FOUND);
  }
}
