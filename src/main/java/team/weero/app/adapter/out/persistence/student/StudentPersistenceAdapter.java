package team.weero.app.adapter.out.persistence.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.application.port.out.student.StudentPort;
import team.weero.app.adapter.out.persistence.student.mapper.StudentMapper;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentPort {

    private final StudentJpaRepository jpaRepository;
    private final StudentMapper mapper;

    @Override
    public Optional<Student> findByAccountId(String accountId) {
        return jpaRepository.findByAccountId(accountId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Student> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Student save(Student student) {
        var entity = mapper.toEntity(student);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
