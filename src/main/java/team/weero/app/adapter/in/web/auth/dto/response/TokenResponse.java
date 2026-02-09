package team.weero.app.adapter.in.web.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "토큰 응답")
public record TokenResponse(
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String refreshToken,
    @Schema(description = "액세스 토큰 만료 일시", example = "2024-01-01T11:00:00")
        LocalDateTime accessTokenExpiredAt,
    @Schema(description = "리프레시 토큰 만료 일시", example = "2024-01-08T10:00:00")
        LocalDateTime refreshTokenExpiredAt) {}
