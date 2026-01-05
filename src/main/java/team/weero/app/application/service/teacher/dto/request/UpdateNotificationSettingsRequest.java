package team.weero.app.application.service.teacher.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 알림 설정 업데이트 요청 DTO
 */
public record UpdateNotificationSettingsRequest(
    @NotBlank(message = "알림 시작 시간은 필수입니다")
    @Size(max = 255, message = "알림 시작 시간은 255자 이하여야 합니다")
    String noNotificationStartTime,

    @NotBlank(message = "알림 종료 시간은 필수입니다")
    @Size(max = 255, message = "알림 종료 시간은 255자 이하여야 합니다")
    String noNotificationEndTime
) {
}
