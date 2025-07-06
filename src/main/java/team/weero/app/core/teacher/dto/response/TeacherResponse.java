package team.weero.app.core.teacher.dto.response;

import java.util.UUID;

public record TeacherResponse(
    UUID id,
    String name,
    String accountId,
    String deviceToken,
    String noNotificationStartTime,
    String noNotificationEndTime
) {
}
