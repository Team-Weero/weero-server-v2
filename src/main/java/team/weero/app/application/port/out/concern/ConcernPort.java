package team.weero.app.application.port.out.concern;

import team.weero.app.domain.concern.model.Concern;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConcernPort {
    Concern save(Concern concern);
    Optional<Concern> findById(UUID id);
    List<Concern> findByStudentId(UUID studentId);
    List<Concern> findByIsResolved(boolean isResolved);
    List<Concern> findAll();
    void deleteById(UUID id);
}
