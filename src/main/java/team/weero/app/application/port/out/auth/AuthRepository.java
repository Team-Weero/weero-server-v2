package team.weero.app.application.port.out.auth;

import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.user.model.User;

import java.util.Optional;

/**
 * Auth Repository Interface (Domain Layer)
 * Defines the contract for authentication-related persistence operations
 */
public interface AuthRepository {

    /**
     * Find a student by account ID
     * @param accountId Account ID
     * @return Optional of Student
     */
    Optional<Student> findStudentByAccountId(String accountId);

    /**
     * Find a student with user for authentication (temporary until full migration)
     * @param accountId Account ID
     * @return Optional of StudentJpaEntity with User
     */
    Optional<team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity> findStudentWithUserByAccountId(String accountId);

    /**
     * Find a teacher by account ID
     * Note: Teacher is still using old JPA entity, will be migrated later
     * @param accountId Account ID
     * @return Optional of Teacher entity
     */
    Optional<team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity> findTeacherByAccountId(String accountId);

    /**
     * Save a user
     * @param user User to save
     * @return Saved user
     */
    User saveUser(User user);

    /**
     * Save a student
     * @param student Student to save
     * @return Saved student
     */
    Student saveStudent(Student student);

    /**
     * Save a teacher
     * Note: Teacher is still using old JPA entity, will be migrated later
     * @param teacher Teacher to save
     * @return Saved teacher entity
     */
    team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity saveTeacher(team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity teacher);
}
