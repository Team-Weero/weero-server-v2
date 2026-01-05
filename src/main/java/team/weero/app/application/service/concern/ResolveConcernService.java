package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.ResolveConcernUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernRepository;

import java.util.UUID;

@Service
@Transactional
public class ResolveConcernService implements ResolveConcernUseCase {

    private final ConcernRepository concernRepository;

    public ResolveConcernService(ConcernRepository concernRepository) {
        this.concernRepository = concernRepository;
    }

    public void execute(UUID id) {
        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        concern.resolve();
        concernRepository.save(concern);
    }
}
