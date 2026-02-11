package team.weero.app.adapter.in.web.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import team.weero.app.application.port.in.auth.dto.request.SignUpCommand;
import team.weero.app.domain.auth.type.Authority;

@Schema(description = "회원가입 요청")
public record SignUpRequest(
    @Schema(
            description = "이메일",
            example = "user@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Email
        String email,
    @Schema(
            description = "비밀번호",
            example = "password123",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String password,
    @Schema(description = "이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String name,
    @Schema(
            description = "권한",
            example = "STUDENT",
            allowableValues = {"STUDENT", "TEACHER"},
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Authority authority,
    @Schema(description = "계정 ID (학생의 경우)", example = "student123") String accountId,
    @Schema(description = "닉네임 (학생의 경우)", example = "길동이") String nickname,
    @Schema(description = "학년 (학생의 경우)", example = "1") Integer grade,
    @Schema(description = "반 (학생의 경우)", example = "2") Integer classRoom,
    @Schema(description = "번호 (학생의 경우)", example = "15") Integer number,
    @Schema(description = "디바이스 토큰 (푸시 알림용)", example = "device_token_abc123") String deviceToken) {

  public static SignUpCommand from(SignUpRequest request) {
    return new SignUpCommand(
        request.email(),
        request.password(),
        request.name(),
        request.authority(),
        request.accountId(),
        request.nickname(),
        request.grade(),
        request.classRoom(),
        request.number(),
        request.deviceToken());
  }
}
