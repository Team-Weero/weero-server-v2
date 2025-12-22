package team.weero.app.domain.student.repository;

import team.weero.app.domain.student.model.Student;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    Optional<Student> findByAccountId(String accountId);
    Optional<Student> findById(UUID id);
    Student save(Student student);
}
