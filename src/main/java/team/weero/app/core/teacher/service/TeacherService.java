package team.weero.app.core.teacher.service;

import team.weero.app.core.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.core.teacher.dto.response.TeacherResponse;

public interface TeacherService {
    TeacherResponse getMyInfo(String accountId);
    void updateNotificationSettings(String accountId, UpdateNotificationSettingsRequest request);
}
