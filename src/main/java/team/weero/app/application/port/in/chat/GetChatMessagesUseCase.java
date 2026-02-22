package team.weero.app.application.port.in.chat;

import java.util.UUID;
import org.springframework.data.domain.Page;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;

public interface GetChatMessagesUseCase {
  Page<ChatMessageInfo> execute(UUID userId, UUID chatRoomId, int page, int size);
}
