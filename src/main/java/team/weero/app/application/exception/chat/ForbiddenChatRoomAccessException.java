package team.weero.app.application.exception.chat;

import team.weero.app.application.exception.chat.error.ChatErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class ForbiddenChatRoomAccessException extends WeeRoException {
  public static final ForbiddenChatRoomAccessException INSTANCE =
      new ForbiddenChatRoomAccessException();

  public ForbiddenChatRoomAccessException() {
    super(ChatErrorCode.FORBIDDEN_CHAT_ROOM_ACCESS);
  }
}
