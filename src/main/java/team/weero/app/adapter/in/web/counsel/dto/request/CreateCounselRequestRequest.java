package team.weero.app.adapter.in.web.counsel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import team.weero.app.domain.counsel.type.Gender;

public record CreateCounselRequestRequest(
    @NotNull(message = "성별은 필수 값입니다.") Gender gender,
    @NotNull(message = "상담 경험 여부는 필수 값입니다.") Boolean hasCounselingExperience,
    @NotBlank(message = "카테고리는 필수 값입니다.") @Size(max = 20, message = "카테고리는 20자 이하여야 합니다.")
        String category,
    @NotNull(message = "담당 선생님 ID는 필수 값입니다.") UUID teacherId) {}
