package team.weero.app.adapter.in.web.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

@Schema(description = "로그인 응답")
public record SignInResponse(
    @Schema(description = "사용자 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID userId,
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String refreshToken,
    @Schema(description = "액세스 토큰 만료 일시", example = "2024-01-01T11:00:00")
        LocalDateTime accessTokenExpiredAt,
    @Schema(description = "리프레시 토큰 만료 일시", example = "2024-01-08T10:00:00")
        LocalDateTime refreshTokenExpiredAt,
    @Schema(
            description = "권한",
            example = "STUDENT",
            allowableValues = {"STUDENT", "TEACHER"})
        Authority authority) {}
