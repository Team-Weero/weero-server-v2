package team.weero.app.domain.chat.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.WeeRoException;

public class ChatRoomNotFoundException extends WeeRoException {
    public static final WeeRoException EXCEPTION = new ChatRoomNotFoundException();

    private ChatRoomNotFoundException() {
        super(ErrorCode.CHAT_ROOM_NOT_FOUND);
    }
}
