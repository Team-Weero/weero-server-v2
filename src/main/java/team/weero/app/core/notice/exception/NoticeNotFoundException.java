package team.weero.app.core.notice.exception;

import team.weero.app.infrastructure.error.exception.WeeRoException;
import team.weero.app.infrastructure.error.exception.ErrorCode;

public class NoticeNotFoundException extends WeeRoException {
    public static final WeeRoException EXCEPTION = new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
