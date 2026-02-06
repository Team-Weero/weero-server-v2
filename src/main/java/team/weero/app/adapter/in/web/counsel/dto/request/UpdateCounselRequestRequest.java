package team.weero.app.adapter.in.web.counsel.dto.request;

import jakarta.validation.constraints.Size;
import java.util.UUID;
import team.weero.app.domain.counsel.type.Gender;

public record UpdateCounselRequestRequest(
    Gender gender,
    Boolean hasCounselingExperience,
    @Size(max = 20) String category,
    UUID teacherId) {}
