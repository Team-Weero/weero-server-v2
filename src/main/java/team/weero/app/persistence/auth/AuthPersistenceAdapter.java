package team.weero.app.persistence.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.core.auth.spi.CommandAuthPort;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.repository.StudentRepository;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.teacher.repository.TeacherRepository;
import team.weero.app.persistence.user.entity.User;
import team.weero.app.persistence.user.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthPersistenceAdapter implements CommandAuthPort {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Optional<Student> findByAccountId (String accountId) {
        return studentRepository.findByAccountId(accountId);
    }

    @Override
    public Optional<Teacher> findTeacherByAccountId(String accountId) {
        return teacherRepository.findByAccountId(accountId);
    }

    @Override
    public User save (User user) {
        return userRepository.save(user);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
