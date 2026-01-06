package team.weero.app.adapter.out.persistence.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.weero.app.application.port.out.auth.AuthRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.application.port.out.student.StudentRepository;
import team.weero.app.domain.user.model.User;
import team.weero.app.application.port.out.user.UserRepository;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.persistence.teacher.repository.TeacherJpaRepository;

import java.util.Optional;


 * Auth Repository Implementation
 * Infrastructure layer implementation of AuthRepository
 * Aggregates User, Student, and Teacher repositories
 */
@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final TeacherJpaRepository teacherRepository;
    private final StudentJpaRepository studentJpaRepository;

    @Override
    public Optional<Student> findStudentByAccountId(String accountId) {
        return studentRepository.findByAccountId(accountId);
    }

    @Override
    public Optional<StudentJpaEntity> findStudentWithUserByAccountId(String accountId) {
        return studentJpaRepository.findByAccountId(accountId);
    }

    @Override
    public Optional<TeacherJpaEntity> findTeacherByAccountId(String accountId) {
        return teacherRepository.findByAccountId(accountId);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public TeacherJpaEntity saveTeacher(TeacherJpaEntity teacher) {
        return teacherRepository.save(teacher);
    }
}
