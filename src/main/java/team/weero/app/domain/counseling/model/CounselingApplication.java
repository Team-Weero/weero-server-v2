package team.weero.app.domain.counseling.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CounselingApplication {
  private UUID id;
  private UUID studentId;
  private UUID teacherId;
  private LocalTime time;
  private LocalDate counselDate;
  private LocalDate applicationDate;
  private CounselingLocation location;
  private boolean isChecked;

  public static CounselingApplication create(
      UUID studentId,
      UUID teacherId,
      LocalTime time,
      LocalDate counselDate,
      CounselingLocation location) {
    return CounselingApplication.builder()
        .studentId(studentId)
        .teacherId(teacherId)
        .time(time)
        .counselDate(counselDate)
        .location(location)
        .applicationDate(LocalDate.now())
        .isChecked(false)
        .build();
  }

  public void markAsChecked() {
    this.isChecked = true;
  }
}
