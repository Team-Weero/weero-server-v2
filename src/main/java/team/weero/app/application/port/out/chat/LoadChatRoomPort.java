package team.weero.app.application.port.out.chat;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.chat.ChatRoom;

public interface LoadChatRoomPort {
  Optional<ChatRoom> loadById(UUID chatRoomId);
}
