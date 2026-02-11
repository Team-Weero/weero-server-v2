package team.weero.app.domain.post.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Post {
  private final UUID id;
  private final String title;
  private final String content;
  private final UUID studentId;
  private String nickName;
  private int viewCount;
  private int heart;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public Post(
      UUID id,
      String title,
      String content,
      UUID studentId,
      String nickName,
      int viewCount,
      int heart,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      LocalDateTime deletedAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.studentId = studentId;
    this.nickName = nickName;
    this.viewCount = viewCount;
    this.heart = heart;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }

  public boolean isDeleted() {
    return deletedAt != null;
  }

  public void increaseViewCount() {
    this.viewCount += 1;
  }
}
