package team.weero.app.domain.post.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Post {
  private final UUID id;
  private final String title;
  private final String content;
  private final UUID studentId;
  private String nickName;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public boolean isDeleted() {
    return deletedAt != null;
  }
}
