package team.weero.app.adapter.in.web.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

@Schema(description = "토큰 클레임 정보")
public record TokenClaims(
    @Schema(description = "사용자 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID userId,
    @Schema(description = "이메일", example = "user@example.com") String email,
    @Schema(
            description = "권한",
            example = "STUDENT",
            allowableValues = {"STUDENT", "TEACHER"})
        Authority authority) {}
