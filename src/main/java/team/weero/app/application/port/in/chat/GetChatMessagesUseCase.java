package team.weero.app.application.port.in.chat;

import java.util.List;
import java.util.UUID;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;

public interface GetChatMessagesUseCase {
  List<ChatMessageInfo> execute(UUID chatRoomId);
}
