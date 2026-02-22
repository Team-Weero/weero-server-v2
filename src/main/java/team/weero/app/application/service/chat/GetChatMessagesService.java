package team.weero.app.application.service.chat;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.weero.app.application.exception.chat.ChatRoomNotFoundException;
import team.weero.app.application.exception.chat.ForbiddenChatRoomAccessException;
import team.weero.app.application.port.in.chat.GetChatMessagesUseCase;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;
import team.weero.app.application.port.out.chat.LoadChatMessagePort;
import team.weero.app.application.port.out.chat.LoadChatRoomPort;
import team.weero.app.domain.chat.ChatMessage;
import team.weero.app.domain.chat.ChatRoom;

@Service
@RequiredArgsConstructor
public class GetChatMessagesService implements GetChatMessagesUseCase {

  private final LoadChatMessagePort loadChatMessagePort;
  private final LoadChatRoomPort loadChatRoomPort;

  @Override
  public Page<ChatMessageInfo> execute(UUID userId, UUID chatRoomId, int page, int size) {
    ChatRoom chatRoom =
        loadChatRoomPort.loadById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

    if (!chatRoom.isParticipant(userId)) {
      throw new ForbiddenChatRoomAccessException();
    }

    Pageable pageable = PageRequest.of(page, size, Sort.by("sendDate").descending());

    Page<ChatMessage> messages = loadChatMessagePort.loadByChatRoomId(chatRoomId, pageable);

    return messages.map(ChatMessageInfo::from);
  }
}
