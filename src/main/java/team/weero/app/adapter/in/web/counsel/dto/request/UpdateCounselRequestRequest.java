package team.weero.app.adapter.in.web.counsel.dto.request;

import jakarta.validation.constraints.Size;
import team.weero.app.domain.counsel.type.Gender;

import java.util.UUID;

public record UpdateCounselRequestRequest(
        @Size(max = 255) String accessPassword,
        Gender gender,
        Boolean hasCounselingExperience,
        @Size(max = 20) String category,
        UUID teacherId) {
}
