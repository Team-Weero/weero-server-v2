package team.weero.app.application.port.out.chat;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.domain.chat.ChatMessage;

public interface LoadChatMessagePort {
  Page<ChatMessage> loadByChatRoomId(UUID chatRoomId, Pageable pageable);
}
