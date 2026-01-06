package team.weero.app.application.port.in.teacher;

import team.weero.app.application.service.teacher.dto.request.UpdateNotificationSettingsRequest;

public interface UpdateNotificationSettingsUseCase {
  void execute(String accountId, UpdateNotificationSettingsRequest request);
}
