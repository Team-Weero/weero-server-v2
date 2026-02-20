package team.weero.app.application.port.out.chat;

import java.util.List;
import java.util.UUID;
import team.weero.app.domain.chat.ChatMessage;

public interface LoadChatMessagePort {
  List<ChatMessage> loadByChatRoomId(UUID chatRoomId);
}
