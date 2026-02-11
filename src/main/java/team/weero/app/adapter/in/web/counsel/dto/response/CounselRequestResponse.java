package team.weero.app.adapter.in.web.counsel.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestListInfo;
import team.weero.app.domain.counsel.type.Gender;
import team.weero.app.domain.counsel.type.Status;

@Schema(description = "상담 요청 응답")
public record CounselRequestResponse(
    @Schema(description = "상담 요청 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID id,
    @Schema(
            description = "상담 상태",
            example = "PENDING",
            allowableValues = {"PENDING", "IN_PROGRESS", "COMPLETED", "CANCELLED"})
        Status status,
    @Schema(
            description = "성별",
            example = "MALE",
            allowableValues = {"MALE", "FEMALE"})
        Gender gender,
    @Schema(description = "상담 경험 여부", example = "true") boolean hasCounselingExperience,
    @Schema(description = "상담 카테고리", example = "진로") String category,
    @Schema(description = "학생 ID", example = "550e8400-e29b-41d4-a716-446655440001") UUID studentId,
    @Schema(description = "학생 이름", example = "홍길동") String studentName,
    @Schema(description = "교사 ID", example = "550e8400-e29b-41d4-a716-446655440002") UUID teacherId,
    @Schema(description = "교사 이름", example = "김선생") String teacherName,
    @Schema(description = "생성 일시", example = "2024-01-01T10:00:00") LocalDateTime createdAt,
    @Schema(description = "수정 일시", example = "2024-01-01T10:00:00") LocalDateTime updatedAt) {

  public static CounselRequestResponse from(CounselRequestInfo info) {
    return new CounselRequestResponse(
        info.id(),
        info.status(),
        info.gender(),
        info.hasCounselingExperience(),
        info.category(),
        info.studentId(),
        info.studentName(),
        info.teacherId(),
        info.teacherName(),
        info.createdAt(),
        info.updatedAt());
  }

  public static List<CounselRequestResponse> fromList(CounselRequestListInfo listInfo) {
    return listInfo.requests().stream().map(CounselRequestResponse::from).toList();
  }
}
