package team.weero.app.application.port.in.chat;

import java.util.List;
import java.util.UUID;
import team.weero.app.application.service.chat.dto.response.MessageResponse;

public interface GetChatRoomMessagesUseCase {
    List<MessageResponse> execute(UUID chatRoomId, String accountId);
}
