package team.weero.app.application.port.out.student;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.student.model.Student;

public interface StudentPort {
  Optional<Student> findByAccountId(String accountId);

  Optional<Student> findById(UUID id);

  Student save(Student student);
}
