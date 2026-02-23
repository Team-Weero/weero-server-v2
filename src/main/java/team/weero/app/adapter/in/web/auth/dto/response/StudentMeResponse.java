package team.weero.app.adapter.in.web.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.domain.student.type.StudentRole;

@Schema(description = "학생 사용자 정보 응답")
public record StudentMeResponse(
    @Schema(description = "사용자 ID") UUID id,
    @Schema(description = "이메일") String email,
    @Schema(description = "권한", example = "STUDENT") Authority authority,
    @Schema(description = "이름", example = "홍길동") String name,
    @Schema(description = "닉네임", example = "길동이") String nickname,
    @Schema(description = "계정 ID", example = "student123") String accountId,
    @Schema(description = "학년", example = "1") int grade,
    @Schema(description = "반", example = "2") int classRoom,
    @Schema(description = "번호", example = "15") int number,
    @Schema(description = "학생 역할", example = "COMMON") StudentRole role) {}
