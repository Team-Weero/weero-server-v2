package team.weero.app.application.service.teacher;
import team.weero.app.application.port.in.teacher.UpdateNotificationSettingsUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.application.port.out.teacher.TeacherRepository;

/**
 * 알림 설정 업데이트 Use Case
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UpdateNotificationSettingsService implements UpdateNotificationSettingsUseCase {

    private final TeacherRepository teacherRepository;

    /**
     * 알림 설정 업데이트
     * @param accountId 계정 ID
     * @param request 알림 설정 요청
     */
    public void execute(String accountId, UpdateNotificationSettingsRequest request) {
        Teacher teacher = teacherRepository.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        teacher.updateNotificationSettings(
                request.noNotificationStartTime(),
                request.noNotificationEndTime()
        );

        teacherRepository.save(teacher);
    }
}
