package team.weero.app.application.port.in.concern;

import team.weero.app.application.service.concern.dto.request.CreateConcernRequest;
import team.weero.app.domain.concern.model.Concern;

public interface CreateConcernUseCase {
    void execute(CreateConcernRequest request, String accountId);
}
