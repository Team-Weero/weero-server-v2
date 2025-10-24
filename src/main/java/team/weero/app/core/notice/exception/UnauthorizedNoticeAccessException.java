package team.weero.app.core.notice.exception;

import team.weero.app.infrastructure.error.exception.CustomException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class UnauthorizedNoticeAccessException extends CustomException {
    public static final CustomException EXCEPTION = new UnauthorizedNoticeAccessException();

    private UnauthorizedNoticeAccessException() {
        super(ErrorCode.UNAUTHORIZED_NOTICE_ACCESS);
    }
}
