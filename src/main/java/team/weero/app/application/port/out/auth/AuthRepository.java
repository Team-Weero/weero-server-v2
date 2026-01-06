package team.weero.app.application.port.out.auth;

import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.user.model.User;

import java.util.Optional;

public interface AuthRepository {

    Optional<Student> findStudentByAccountId(String accountId);

    Optional<StudentJpaEntity> findStudentWithUserByAccountId(String accountId);

    Optional<TeacherJpaEntity> findTeacherByAccountId(String accountId);

    User saveUser(User user);

    Student saveStudent(Student student);
    
    TeacherJpaEntity saveTeacher(team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity teacher);
}
