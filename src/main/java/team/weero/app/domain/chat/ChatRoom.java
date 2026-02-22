package team.weero.app.domain.chat;

import java.time.LocalDateTime;
import java.util.Objects;
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

  /** 일반 equals는 호출 대상이 null이면 NPE 발생 Objects.equals는 null이어도 안전하게 비교함 */
  public boolean isParticipant(UUID userId) {
    return Objects.equals(teacherId, userId) || Objects.equals(studentId, userId);
  }
}
