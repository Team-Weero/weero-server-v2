package team.weero.app.application.port.in.chat;

import team.weero.app.application.service.chat.dto.request.CreateChatRoomRequest;
import team.weero.app.application.service.chat.dto.response.ChatRoomResponse;
import team.weero.app.domain.chat.model.ChatRoom;

public interface CreateChatRoomUseCase {
    ChatRoomResponse execute(CreateChatRoomRequest request, String accountId);
}
