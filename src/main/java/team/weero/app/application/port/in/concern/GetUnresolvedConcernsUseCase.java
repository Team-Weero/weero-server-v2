package team.weero.app.application.port.in.concern;

import java.util.List;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;

public interface GetUnresolvedConcernsUseCase {
  List<ConcernResponse> execute();
}
