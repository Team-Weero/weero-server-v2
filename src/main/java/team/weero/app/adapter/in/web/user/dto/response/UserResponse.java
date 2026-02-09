package team.weero.app.adapter.in.web.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

@Schema(description = "사용자 응답")
public record UserResponse(
    @Schema(description = "사용자 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID id,
    @Schema(description = "이메일", example = "user@example.com") String email,
    @Schema(
            description = "권한",
            example = "STUDENT",
            allowableValues = {"STUDENT", "TEACHER"})
        Authority authority) {}
