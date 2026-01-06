package team.weero.app.application.service.teacher.dto.response;

import team.weero.app.domain.teacher.model.Teacher;

import java.util.UUID;

public record TeacherResponse(
    UUID id,
    String name,
    String accountId,
    String deviceToken,
    String noNotificationStartTime,
    String noNotificationEndTime
) {
    public static TeacherResponse from(Teacher teacher) {
        return new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getAccountId(),
                teacher.getDeviceToken(),
                teacher.getNoNotificationStartTime(),
                teacher.getNoNotificationEndTime()
        );
    }
}
