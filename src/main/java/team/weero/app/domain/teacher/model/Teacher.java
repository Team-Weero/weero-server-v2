package team.weero.app.domain.teacher.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

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

    public void updateNotificationSettings(String startTime, String endTime) {
        this.noNotificationStartTime = startTime;
        this.noNotificationEndTime = endTime;
    }

    public void updateDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
