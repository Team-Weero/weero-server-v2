package team.weero.app.application.port.out.teacher;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.teacher.model.Teacher;

public interface TeacherPort {

  Optional<Teacher> findByAccountId(String accountId);

  Optional<Teacher> findById(UUID id);

  Optional<Teacher> findByUserId(UUID userId);

  Teacher save(Teacher teacher);

  boolean existsByAccountId(String accountId);
}
