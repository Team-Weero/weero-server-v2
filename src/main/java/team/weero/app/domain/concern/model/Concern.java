package team.weero.app.domain.concern.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Concern {
  private final UUID id;
  private final UUID studentId;
  private final String title;
  private final String contents;
  private boolean isResolved;
  private final LocalDateTime createdAt;

  public Concern(
      UUID id,
      UUID studentId,
      String title,
      String contents,
      boolean isResolved,
      LocalDateTime createdAt) {
    this.id = id;
    this.studentId = studentId;
    this.title = title;
    this.contents = contents;
    this.isResolved = isResolved;
    this.createdAt = createdAt;
  }

  public static Concern create(UUID studentId, String title, String contents) {
    return new Concern(null, studentId, title, contents, false, LocalDateTime.now());
  }

  public void resolve() {
    this.isResolved = true;
  }

  public boolean isOwnedBy(UUID studentId) {
    return this.studentId.equals(studentId);
  }

  public UUID getId() {
    return id;
  }

  public UUID getStudentId() {
    return studentId;
  }

  public String getTitle() {
    return title;
  }

  public String getContents() {
    return contents;
  }

  public boolean isResolved() {
    return isResolved;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
