package team.weero.app.application.service.chat;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.weero.app.application.port.in.chat.GetChatMessagesUseCase;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;
import team.weero.app.application.port.out.chat.LoadChatMessagePort;

@Service
@RequiredArgsConstructor
public class GetChatMessagesService implements GetChatMessagesUseCase {

  private final LoadChatMessagePort loadChatMessagePort;

  @Override
  public List<ChatMessageInfo> execute(UUID chatRoomId, int page, int size) {

    Pageable pageable = PageRequest.of(page, size, Sort.by("sendDate").descending());

    return loadChatMessagePort.loadByChatRoomId(chatRoomId, pageable).stream()
        .map(ChatMessageInfo::from)
        .toList();
  }
}
