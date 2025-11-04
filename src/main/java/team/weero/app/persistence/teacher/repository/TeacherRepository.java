package team.weero.app.persistence.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Optional<Teacher> findByAccountId(String accountId);
    Optional<Teacher> findByUser(User user);
    boolean existsByAccountId(String accountId);
}
