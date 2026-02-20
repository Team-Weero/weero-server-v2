package team.weero.app.application.port.out.chat;

import team.weero.app.domain.chat.ChatRoom;

public interface SaveChatRoomPort {
  ChatRoom save(ChatRoom chatRoom);
}
