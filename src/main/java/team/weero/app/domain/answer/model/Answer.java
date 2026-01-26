package team.weero.app.domain.answer.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Answer {
  private UUID id;
  private UUID postId;
  private UUID userId;
  private String answer;
  private String nickName;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public boolean isDeleted() {
    return deletedAt != null;
  }
}
