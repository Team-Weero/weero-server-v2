package team.weero.app.adapter.in.web.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "로그인 요청")
public record SignInRequest(
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
        String password) {}
