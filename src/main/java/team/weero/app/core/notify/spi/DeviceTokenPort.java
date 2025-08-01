package team.weero.app.core.notify.spi;

import team.weero.app.infrastructure.firebase.entity.DeviceToken;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.util.Optional;

public interface DeviceTokenPort {
    Optional<DeviceToken> findByTeacher(Teacher teacher);
}
