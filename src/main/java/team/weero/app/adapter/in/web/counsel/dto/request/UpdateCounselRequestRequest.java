package team.weero.app.adapter.in.web.counsel.dto.request;

import jakarta.validation.constraints.Size;
import java.util.UUID;
import team.weero.app.domain.counsel.type.Gender;

public record UpdateCounselRequestRequest(
    Gender gender,
    Boolean hasCounselingExperience,
    @Size(min = 1, max = 20, message = "카테고리는 1자 이상 20자 이하여야 합니다.") String category,
    UUID teacherId) {}
