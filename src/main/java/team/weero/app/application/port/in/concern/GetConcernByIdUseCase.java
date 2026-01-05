package team.weero.app.application.port.in.concern;

import java.util.UUID;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;
import team.weero.app.domain.concern.model.Concern;

public interface GetConcernByIdUseCase {
    ConcernResponse execute(UUID id);
}
