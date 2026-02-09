package team.weero.app.adapter.in.web.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import team.weero.app.domain.auth.type.Authority;

@Schema(description = "회원가입 커맨드")
public record SignUpCommand(
    @Schema(description = "이메일", example = "user@example.com") String email,
    @Schema(description = "비밀번호", example = "password123") String password,
    @Schema(description = "이름", example = "홍길동") String name,
    @Schema(
            description = "권한",
            example = "STUDENT",
            allowableValues = {"STUDENT", "TEACHER"})
        Authority authority,
    @Schema(description = "계정 ID (학생의 경우)", example = "student123") String accountId,
    @Schema(description = "닉네임 (학생의 경우)", example = "길동이") String nickname,
    @Schema(description = "학년 (학생의 경우)", example = "1") Integer grade,
    @Schema(description = "반 (학생의 경우)", example = "2") Integer classRoom,
    @Schema(description = "번호 (학생의 경우)", example = "15") Integer number,
    @Schema(description = "디바이스 토큰 (푸시 알림용)", example = "device_token_abc123")
        String deviceToken) {}
