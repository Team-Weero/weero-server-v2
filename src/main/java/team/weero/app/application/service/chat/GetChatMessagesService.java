package team.weero.app.application.service.chat;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.application.port.in.chat.GetChatMessagesUseCase;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;
import team.weero.app.application.port.out.chat.LoadChatMessagePort;

@Service
@RequiredArgsConstructor
public class GetChatMessagesService implements GetChatMessagesUseCase {

  private final LoadChatMessagePort loadChatMessagePort;

  @Override
  public List<ChatMessageInfo> execute(UUID chatRoomId) {
    return loadChatMessagePort.loadByChatRoomId(chatRoomId).stream()
        .map(ChatMessageInfo::from)
        .toList();
  }
}
