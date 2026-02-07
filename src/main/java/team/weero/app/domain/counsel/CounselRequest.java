package team.weero.app.domain.counsel;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.weero.app.application.exception.counsel.InvalidCounselRequestStatusException;
import team.weero.app.domain.counsel.type.Gender;
import team.weero.app.domain.counsel.type.Status;

@Getter
@Builder
@AllArgsConstructor
public class CounselRequest {

  private final UUID id;
  private final Status status;
  private final Gender gender;
  private final boolean hasCounselingExperience;
  private final String category;
  private final UUID studentId;
  private final String studentName;
  private final UUID teacherId;
  private final String teacherName;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
  private final LocalDateTime deletedAt;

  public boolean isDeleted() {
    return deletedAt != null;
  }

  public boolean isPending() {
    return status == Status.PENDING;
  }

  public boolean isInProgress() {
    return status == Status.IN_PROGRESS;
  }

  public boolean isCompleted() {
    return status == Status.COMPLETED;
  }

  public boolean isCancelled() {
    return status == Status.CANCELLED;
  }

  public CounselRequest approve() {
    if (!isPending()) {
      throw new InvalidCounselRequestStatusException();
    }
    return CounselRequest.builder()
        .id(this.id)
        .status(Status.IN_PROGRESS)
        .gender(this.gender)
        .hasCounselingExperience(this.hasCounselingExperience)
        .category(this.category)
        .studentId(this.studentId)
        .studentName(this.studentName)
        .teacherId(this.teacherId)
        .teacherName(this.teacherName)
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .deletedAt(this.deletedAt)
        .build();
  }

  public CounselRequest reject() {
    if (!isPending()) {
      throw new InvalidCounselRequestStatusException();
    }
    return CounselRequest.builder()
        .id(this.id)
        .status(Status.CANCELLED)
        .gender(this.gender)
        .hasCounselingExperience(this.hasCounselingExperience)
        .category(this.category)
        .studentId(this.studentId)
        .studentName(this.studentName)
        .teacherId(this.teacherId)
        .teacherName(this.teacherName)
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .deletedAt(this.deletedAt)
        .build();
  }

  public void validateCancellable() {
    if (!isPending()) {
      throw new InvalidCounselRequestStatusException();
    }
    if (isDeleted()) {
      throw new InvalidCounselRequestStatusException();
    }
  }
}
