package team.weero.app.domain.chat;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChatMessage {
  private final UUID id;
  private final UUID chatRoomId;
  private final UUID senderId;
  private final String text;
  private final LocalDateTime sendDate;
}
