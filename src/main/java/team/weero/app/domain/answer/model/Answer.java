package team.weero.app.domain.answer.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Answer {
  private UUID id;
  private UUID concernId;
  private UUID studentId;
  private String content;
  private LocalDateTime createdAt;

  public boolean isWrittenBy(UUID studentId) {
    return this.studentId.equals(studentId);
  }
}
