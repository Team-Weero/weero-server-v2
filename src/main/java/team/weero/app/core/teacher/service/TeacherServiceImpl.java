package team.weero.app.core.teacher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.core.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.core.teacher.dto.response.TeacherResponse;
import team.weero.app.core.teacher.exception.TeacherNotFoundException;
import team.weero.app.core.teacher.spi.TeacherPort;
import team.weero.app.persistence.teacher.entity.Teacher;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {
    
    private final TeacherPort teacherPort;

    @Override
    public TeacherResponse getMyInfo(String accountId) {
        Teacher teacher = teacherPort.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        return new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getAccountId(),
                teacher.getDeviceToken(),
                teacher.getNoNotificationStartTime(),
                teacher.getNoNotificationEndTime()
        );
    }

    @Override
    @Transactional
    public void updateNotificationSettings(String accountId, UpdateNotificationSettingsRequest request) {
        Teacher teacher = teacherPort.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        teacher.updateNotificationSettings(
                request.noNotificationStartTime(),
                request.noNotificationEndTime()
        );

        teacherPort.save(teacher);
    }
}
