package team.weero.app.persistence.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.core.teacher.spi.TeacherPort;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.teacher.repository.TeacherRepository;
import team.weero.app.persistence.user.entity.User;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TeacherPersistenceAdapter implements TeacherPort {

    private final TeacherRepository teacherRepository;

    @Override
    public Optional<Teacher> findByAccountId(String accountId) {
        return teacherRepository.findByAccountId(accountId);
    }

    @Override
    public Optional<Teacher> findById(UUID id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teacher> findByUser(User user) {
        return teacherRepository.findByUser(user);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
