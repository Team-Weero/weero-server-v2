package team.weero.app.core.auth.spi;

import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.user.entity.User;

import java.util.Optional;

public interface CommandAuthPort {

    Optional<Student> findByAccountId(String accountId);

    User save(User user);

    Student save(Student student);
}
