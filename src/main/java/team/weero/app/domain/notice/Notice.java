package team.weero.app.domain.notice;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notice {

  private final UUID id;
  private final String title;
  private final String content;
  private final UUID writerId;
  private final LocalDateTime createdAt;

  public Notice(UUID id, String title, String content, UUID writerId, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.writerId = writerId;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public UUID getWriterId() {
    return writerId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
