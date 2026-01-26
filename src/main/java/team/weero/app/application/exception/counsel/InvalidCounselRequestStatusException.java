package team.weero.app.application.exception.counsel;

import team.weero.app.application.exception.counsel.error.CounselRequestErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class InvalidCounselRequestStatusException extends WeeRoException {
    public InvalidCounselRequestStatusException() {
        super(CounselRequestErrorCode.INVALID_COUNSEL_REQUEST_STATUS);
    }
}
