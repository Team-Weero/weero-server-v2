package team.weero.app.application.service.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.teacher.UpdateNotificationSettingsUseCase;
import team.weero.app.application.port.out.teacher.TeacherPort;
import team.weero.app.application.service.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateNotificationSettingsService implements UpdateNotificationSettingsUseCase {

  private final TeacherPort teacherPort;

  public void execute(String accountId, UpdateNotificationSettingsRequest request) {
    Teacher teacher =
        teacherPort
            .findByAccountId(accountId)
            .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

    teacher.updateNotificationSettings(
        request.noNotificationStartTime(), request.noNotificationEndTime());

    teacherPort.save(teacher);
  }
}
