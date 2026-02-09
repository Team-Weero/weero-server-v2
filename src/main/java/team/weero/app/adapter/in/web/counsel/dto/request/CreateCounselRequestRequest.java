package team.weero.app.adapter.in.web.counsel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import team.weero.app.domain.counsel.type.Gender;

@Schema(description = "상담 요청 생성 요청")
public record CreateCounselRequestRequest(
    @Schema(
            description = "성별",
            example = "MALE",
            allowableValues = {"MALE", "FEMALE"},
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "성별은 필수 값입니다.")
        Gender gender,
    @Schema(description = "상담 경험 여부", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "상담 경험 여부는 필수 값입니다.")
        Boolean hasCounselingExperience,
    @Schema(
            description = "상담 카테고리 (최대 20자)",
            example = "진로",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "카테고리는 필수 값입니다.")
        @Size(max = 20, message = "카테고리는 20자 이하여야 합니다.")
        String category,
    @Schema(
            description = "담당 교사 ID",
            example = "550e8400-e29b-41d4-a716-446655440000",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "담당 선생님 ID는 필수 값입니다.")
        UUID teacherId) {}
