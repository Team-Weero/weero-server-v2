package team.weero.app.adapter.out.persistence.auth.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.persistence.teacher.repository.TeacherJpaRepository;
import team.weero.app.application.port.out.auth.AuthPort;
import team.weero.app.application.port.out.student.StudentPort;
import team.weero.app.application.port.out.user.UserPort;
import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.user.model.User;

@Repository
@RequiredArgsConstructor
public class AuthPortImpl implements AuthPort {

  private final StudentPort studentPort;
  private final UserPort userPort;
  private final TeacherJpaRepository teacherRepository;
  private final StudentJpaRepository studentJpaRepository;

  @Override
  public Optional<Student> findStudentByAccountId(String accountId) {
    return studentPort.findByAccountId(accountId);
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
    return userPort.save(user);
  }

  @Override
  public Student saveStudent(Student student) {
    return studentPort.save(student);
  }

  @Override
  public TeacherJpaEntity saveTeacher(TeacherJpaEntity teacher) {
    return teacherRepository.save(teacher);
  }
}
