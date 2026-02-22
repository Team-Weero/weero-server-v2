package team.weero.app.application.exception.chat;

import team.weero.app.application.exception.chat.error.ChatErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class ChatRoomNotFoundException extends WeeRoException {
  public static final ChatRoomNotFoundException INSTANCE = new ChatRoomNotFoundException();

  public ChatRoomNotFoundException() {
    super(ChatErrorCode.CHAT_ROOM_NOT_FOUND);
  }
}
