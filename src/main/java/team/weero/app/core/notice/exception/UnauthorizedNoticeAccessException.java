package team.weero.app.core.notice.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class UnauthorizedNoticeAccessException extends WeeRoException {
    public static final WeeRoException EXCEPTION = new UnauthorizedNoticeAccessException();

    private UnauthorizedNoticeAccessException() {
        super(ErrorCode.UNAUTHORIZED_NOTICE_ACCESS);
    }
}
