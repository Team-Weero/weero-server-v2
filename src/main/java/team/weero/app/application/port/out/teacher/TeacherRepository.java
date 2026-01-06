package team.weero.app.application.port.out.teacher;

import team.weero.app.domain.teacher.model.Teacher;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository {

    Optional<Teacher> findByAccountId(String accountId);

    Optional<Teacher> findById(UUID id);

    Optional<Teacher> findByUserId(UUID userId);

    Teacher save(Teacher teacher);

    boolean existsByAccountId(String accountId);
}
