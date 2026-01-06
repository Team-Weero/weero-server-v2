package team.weero.app.application.port.in.concern;

import java.util.UUID;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;

public interface GetConcernByIdUseCase {
  ConcernResponse execute(UUID id);
}
