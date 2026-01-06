package team.weero.app.adapter.out.persistence.teacher.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;

@Component
public class TeacherMapper {

    public Teacher toDomain(TeacherJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Teacher.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .name(entity.getName())
                .accountId(entity.getAccountId())
                .deviceToken(entity.getDeviceToken())
                .noNotificationStartTime(entity.getNoNotificationStartTime())
                .noNotificationEndTime(entity.getNoNotificationEndTime())
                .build();
    }

    public TeacherJpaEntity toEntity(Teacher domain) {
        if (domain == null) {
            return null;
        }

        return TeacherJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .accountId(domain.getAccountId())
                .deviceToken(domain.getDeviceToken())
                .noNotificationStartTime(domain.getNoNotificationStartTime())
                .noNotificationEndTime(domain.getNoNotificationEndTime())
                .build();
    }

    public void updateEntity(Teacher domain, TeacherJpaEntity entity) {
        if (domain == null || entity == null) {
            return;
        }

        entity.updateNotificationSettings(
                domain.getNoNotificationStartTime(),
                domain.getNoNotificationEndTime()
        );
        entity.updateDeviceToken(domain.getDeviceToken());
    }
}
