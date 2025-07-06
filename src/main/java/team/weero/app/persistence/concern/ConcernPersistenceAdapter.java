package team.weero.app.persistence.concern;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.core.concern.spi.ConcernPort;
import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.concern.repository.ConcernRepository;
import team.weero.app.persistence.student.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ConcernPersistenceAdapter implements ConcernPort {

    private final ConcernRepository concernRepository;

    public ConcernPersistenceAdapter(ConcernRepository concernRepository) {
        this.concernRepository = concernRepository;
    }

    @Override
    public Concern save(Concern concern) {
        return concernRepository.save(concern);
    }

    @Override
    public Optional<Concern> findById(UUID id) {
        return concernRepository.findById(id);
    }

    @Override
    public List<Concern> findByStudent(Student student) {
        return concernRepository.findByStudentOrderByCreatedAtDesc(student);
    }

    @Override
    public List<Concern> findByIsResolved(boolean isResolved) {
        return concernRepository.findByIsResolvedOrderByCreatedAtDesc(isResolved);
    }

    @Override
    public List<Concern> findAll() {
        return concernRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public void deleteById(UUID id) {
        concernRepository.deleteById(id);
    }
}
