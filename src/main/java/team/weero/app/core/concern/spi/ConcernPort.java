package team.weero.app.core.concern.spi;

import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.student.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConcernPort {
    Concern save(Concern concern);
    Optional<Concern> findById(UUID id);
    List<Concern> findByStudent(Student student);
    List<Concern> findByIsResolved(boolean isResolved);
    List<Concern> findAll();
    void deleteById(UUID id);
}
