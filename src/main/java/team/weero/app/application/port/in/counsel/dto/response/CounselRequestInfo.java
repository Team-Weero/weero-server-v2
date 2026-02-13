package team.weero.app.application.port.in.counsel.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.application.port.in.student.dto.response.StudentInfo;
import team.weero.app.application.port.in.teacher.dto.response.TeacherInfo;
import team.weero.app.domain.counsel.CounselRequest;
import team.weero.app.domain.counsel.type.Gender;
import team.weero.app.domain.counsel.type.Status;

public record CounselRequestInfo(
    UUID id,
    Status status,
    Gender gender,
    boolean hasCounselingExperience,
    String category,
    UUID studentId,
    String studentName,
    UUID teacherId,
    String teacherName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

  public static CounselRequestInfo from(
      CounselRequest request, StudentInfo student, TeacherInfo teacher) {
    return new CounselRequestInfo(
        request.getId(),
        request.getStatus(),
        request.getGender(),
        request.isHasCounselingExperience(),
        request.getCategory(),
        request.getStudentId(),
        student.name(),
        request.getTeacherId(),
        teacher.name(),
        request.getCreatedAt(),
        request.getUpdatedAt());
  }

  public static CounselRequestInfo from(CounselRequest request, StudentInfo student) {
    return new CounselRequestInfo(
        request.getId(),
        request.getStatus(),
        request.getGender(),
        request.isHasCounselingExperience(),
        request.getCategory(),
        request.getStudentId(),
        student.name(),
        request.getTeacherId(),
        null, // teacherName 없음
        request.getCreatedAt(),
        request.getUpdatedAt());
  }
}
