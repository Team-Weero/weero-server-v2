package team.weero.app.domain.chat.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Message {
  private UUID id;
  private UUID chatRoomId;
  private UUID senderId;
  private String text;
  private LocalDateTime sendDate;
  private boolean readStatus;

  public boolean isSentBy(UUID userId) {
    return this.senderId.equals(userId);
  }

  public void markAsRead() {
    this.readStatus = true;
  }

  public boolean isUnread() {
    return !this.readStatus;
  }
}
