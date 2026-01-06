package team.weero.app.application.port.out.student;

import team.weero.app.domain.student.model.Student;

import java.util.Optional;
import java.util.UUID;

public interface StudentPort {
    Optional<Student> findByAccountId(String accountId);
    Optional<Student> findById(UUID id);
    Student save(Student student);
}
