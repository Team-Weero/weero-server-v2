package team.weero.app.core.teacher.dto.response;

import team.weero.app.infrastructure.firebase.entity.DeviceToken;

import java.util.List;
import java.util.UUID;

public record TeacherResponse(
    UUID id,
    String name,
    String accountId,
    DeviceToken deviceTokens,
    String noNotificationStartTime,
    String noNotificationEndTime
) {
}
