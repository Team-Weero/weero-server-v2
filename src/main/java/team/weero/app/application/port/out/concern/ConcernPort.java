package team.weero.app.application.port.out.concern;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.concern.model.Concern;

public interface ConcernPort {
  Concern save(Concern concern);

  Optional<Concern> findById(UUID id);

  List<Concern> findByStudentId(UUID studentId);

  List<Concern> findByIsResolved(boolean isResolved);

  List<Concern> findAll();

  void deleteById(UUID id);
}
