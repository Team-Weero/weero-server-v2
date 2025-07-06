package team.weero.app.core.teacher.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.core.teacher.service.TeacherService;

@Usecase
@RequiredArgsConstructor
public class UpdateNotificationSettingsUseCase {

    private final TeacherService teacherService;

    public void execute(String accountId, UpdateNotificationSettingsRequest request) {
        teacherService.updateNotificationSettings(accountId, request);
    }
}
