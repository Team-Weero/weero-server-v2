package team.weero.app.core.teacher.spi;

import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface TeacherPort {
    Optional<Teacher> findByAccountId(String accountId);
    Optional<Teacher> findById(UUID id);
    Optional<Teacher> findByUser(User user);
    Teacher save(Teacher teacher);
}
