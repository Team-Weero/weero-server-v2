package team.weero.app.application.service.teacher.dto.response;

import team.weero.app.domain.teacher.model.Teacher;

import java.util.UUID;

/**
 * Teacher 응답 DTO
 */
public record TeacherResponse(
    UUID id,
    String name,
    String accountId,
    String deviceToken,
    String noNotificationStartTime,
    String noNotificationEndTime
) {
    /**
     * Teacher 도메인 모델로부터 응답 생성
     * @param teacher Teacher 도메인 모델
     * @return TeacherResponse
     */
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
