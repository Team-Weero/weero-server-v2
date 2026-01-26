package team.weero.app.adapter.in.web.counsel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import team.weero.app.domain.counsel.type.Gender;

import java.util.UUID;

public record CreateCounselRequestRequest(
        @NotBlank(message = "비밀번호는 필수 값입니다.") @Size(max = 255) String accessPassword,
        @NotNull(message = "성별은 필수 값입니다.") Gender gender,
        @NotNull(message = "상담 경험 여부는 필수 값입니다.") Boolean hasCounselingExperience,
        @NotBlank(message = "카테고리는 필수 값입니다.") @Size(max = 20) String category,
        @NotNull(message = "담당 선생님 ID는 필수 값입니다.") UUID teacherId) {
}
