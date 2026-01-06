package team.weero.app.adapter.out.persistence.concern;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.concern.repository.ConcernJpaRepository;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.adapter.out.persistence.concern.mapper.ConcernMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ConcernPersistenceAdapter implements ConcernPort {

    private final ConcernJpaRepository concernJpaRepository;
    private final ConcernMapper concernMapper;

    public ConcernPersistenceAdapter(ConcernJpaRepository concernJpaRepository, ConcernMapper concernMapper) {
        this.concernJpaRepository = concernJpaRepository;
        this.concernMapper = concernMapper;
    }

    @Override
    public Concern save(Concern concern) {
        var entity = concernMapper.toEntity(concern);
        var savedEntity = concernJpaRepository.save(entity);
        return concernMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Concern> findById(UUID id) {
        return concernJpaRepository.findById(id)
                .map(concernMapper::toDomain);
    }

    @Override
    public List<Concern> findByStudentId(UUID studentId) {
        return concernJpaRepository.findByStudentIdOrderByCreatedAtDesc(studentId)
                .stream()
                .map(concernMapper::toDomain)
                .toList();
    }

    @Override
    public List<Concern> findByIsResolved(boolean isResolved) {
        return concernJpaRepository.findByIsResolvedOrderByCreatedAtDesc(isResolved)
                .stream()
                .map(concernMapper::toDomain)
                .toList();
    }

    @Override
    public List<Concern> findAll() {
        return concernJpaRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(concernMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        concernJpaRepository.deleteById(id);
    }
}
