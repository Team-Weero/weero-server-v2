package team.weero.app.application.concern.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.domain.concern.repository.ConcernRepository;

import java.util.UUID;

@Service
@Transactional
public class ResolveConcernUseCase {

    private final ConcernRepository concernRepository;

    public ResolveConcernUseCase(ConcernRepository concernRepository) {
        this.concernRepository = concernRepository;
    }

    public void execute(UUID id) {
        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        concern.resolve();
        concernRepository.save(concern);
    }
}
