package team.weero.app.adapter.in.web.student.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import team.weero.app.domain.student.type.StudentRole;

@Schema(description = "학생 정보")
public record StudentInfo(
    @Schema(description = "학생 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID id,
    @Schema(description = "학생 이름", example = "홍길동") String name,
    @Schema(description = "계정 ID", example = "student123") String accountId,
    @Schema(description = "학년", example = "1") int grade,
    @Schema(description = "반", example = "2") int classRoom,
    @Schema(description = "번호", example = "15") int number,
    @Schema(
            description = "학생 역할",
            example = "COMMON",
            allowableValues = {"COMMON", "AGENT"})
        StudentRole role) {}
