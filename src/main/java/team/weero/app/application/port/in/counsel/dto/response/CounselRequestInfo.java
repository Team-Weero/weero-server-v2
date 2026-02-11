package team.weero.app.application.port.in.counsel.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.domain.counsel.type.Gender;
import team.weero.app.domain.counsel.type.Status;

public record CounselRequestInfo(
    Status status,
    Gender gender,
    boolean hasCounselingExperience,
    String category,
    UUID studentId,
    String studentName,
    UUID teacherId,
    String teacherName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
