package team.weero.app.infrastructure.persistence.teacher.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.infrastructure.persistence.teacher.entity.TeacherJpaEntity;

/**
 * Teacher 도메인 모델과 JPA Entity 간 매퍼
 */
@Component
public class TeacherMapper {

    /**
     * JPA Entity를 도메인 모델로 변환
     * @param entity TeacherJpaEntity
     * @return Teacher 도메인 모델
     */
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

    /**
     * 도메인 모델을 JPA Entity로 변환
     * @param domain Teacher 도메인 모델
     * @return TeacherJpaEntity
     */
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

    /**
     * 도메인 모델의 변경사항을 JPA Entity에 적용
     * @param domain Teacher 도메인 모델
     * @param entity TeacherJpaEntity
     */
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
