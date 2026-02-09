package team.weero.app.adapter.in.web.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "로그인 커맨드")
public record SignInCommand(
    @Schema(description = "이메일", example = "user@example.com") String email,
    @Schema(description = "비밀번호", example = "password123") String password) {}
