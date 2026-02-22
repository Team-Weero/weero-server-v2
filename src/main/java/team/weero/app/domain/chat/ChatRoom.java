package team.weero.app.domain.chat;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChatRoom {

  private final UUID id;
  private final UUID counselRequestId;
  private final UUID teacherId;
  private final UUID studentId;
  private final LocalDateTime createdAt;

  public boolean isParticipant(UUID userId) {
    return teacherId.equals(userId) || studentId.equals(userId);
  }
}
