package team.weero.app.application.service.counseling.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import team.weero.app.domain.counseling.model.CounselingApplication;
import team.weero.app.domain.counseling.model.CounselingLocation;

public record CounselingResponse(
    UUID id,
    UUID studentId,
    UUID teacherId,
    LocalTime time,
    LocalDate counselDate,
    LocalDate applicationDate,
    CounselingLocation location,
    boolean isChecked) {
  public static CounselingResponse from(CounselingApplication counseling) {
    return new CounselingResponse(
        counseling.getId(),
        counseling.getStudentId(),
        counseling.getTeacherId(),
        counseling.getTime(),
        counseling.getCounselDate(),
        counseling.getApplicationDate(),
        counseling.getLocation(),
        counseling.isChecked());
  }
}
