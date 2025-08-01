package team.weero.app.core.notify.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.core.notify.spi.DeviceTokenPort;
import team.weero.app.core.notify.spi.NotifyPort;
import team.weero.app.core.notify.type.NotifyType;
import team.weero.app.infrastructure.firebase.DeviceTokenAdapter;
import team.weero.app.infrastructure.firebase.entity.DeviceToken;
import team.weero.app.infrastructure.firebase.service.FcmService;
import team.weero.app.persistence.notify.entity.Notify;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotifyServiceImpl implements NotifyService {

    private final NotifyPort notifyPort;
    private final FcmService fcmService;
    private final DeviceTokenPort deviceTokenPort;

    @Override
    public void notifyCounselingApplied(String studentName, Teacher teacher) {
        NotifyType type = NotifyType.COUNSELING_APPLIED;

        String title = type.getTitle();
        String body = type.createBody(studentName);

        DeviceToken token = deviceTokenPort.findByTeacher(teacher)
                .orElseThrow(() -> new IllegalStateException("No device token found for teacher: " + teacher)); //추후에 예외처리

        Notify notify = Notify.builder()
                .teacher(teacher)
                .title(title)
                .content(body)
                .type(type)
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        notifyPort.save(notify);

        try {
            fcmService.sendMessageTo(token, title, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
