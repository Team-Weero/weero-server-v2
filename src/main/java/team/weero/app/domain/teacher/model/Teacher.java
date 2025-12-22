package team.weero.app.domain.teacher.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * Teacher 도메인 모델
 * DDD의 Aggregate Root
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Teacher {

    private final UUID id;
    private final UUID userId;
    private final String name;
    private final String accountId;
    private String deviceToken;
    private String noNotificationStartTime;
    private String noNotificationEndTime;

    /**
     * 알림 설정 업데이트
     * @param startTime 알림 시작 시간
     * @param endTime 알림 종료 시간
     */
    public void updateNotificationSettings(String startTime, String endTime) {
        this.noNotificationStartTime = startTime;
        this.noNotificationEndTime = endTime;
    }

    /**
     * 디바이스 토큰 업데이트
     * @param deviceToken 디바이스 토큰
     */
    public void updateDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
