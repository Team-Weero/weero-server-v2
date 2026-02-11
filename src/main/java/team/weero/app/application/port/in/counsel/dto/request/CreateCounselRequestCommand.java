package team.weero.app.application.port.in.counsel.dto.request;

import team.weero.app.domain.counsel.type.Gender;

import java.util.UUID;

public record CreateCounselRequestCommand(
        Gender gender,
        Boolean hasCounselingExperience,
        String category,
        UUID teacherId
) {
}
