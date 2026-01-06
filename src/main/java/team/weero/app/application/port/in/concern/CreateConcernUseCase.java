package team.weero.app.application.port.in.concern;

import team.weero.app.application.service.concern.dto.request.CreateConcernRequest;

public interface CreateConcernUseCase {
  void execute(CreateConcernRequest request, String accountId);
}
