package team.weero.app.core.auth.spi;

import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.user.entity.User;

import java.util.Optional;

public interface CommandAuthPort {

    Optional<Student> findByStudentAccountId(String accountId);

    Optional<Teacher> findByTeacherAccountId(String accountId);

    User save(User user);

    Student save(Student student);

    Teacher save(Teacher teacher);
}
