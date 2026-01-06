package team.weero.app.application.service.concern;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.concern.ResolveConcernUseCase;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;

@Service
@Transactional
public class ResolveConcernService implements ResolveConcernUseCase {

  private final ConcernPort concernPort;

  public ResolveConcernService(ConcernPort concernPort) {
    this.concernPort = concernPort;
  }

  public void execute(UUID id) {
    Concern concern =
        concernPort.findById(id).orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

    concern.resolve();
    concernPort.save(concern);
  }
}
