package team.weero.app.application.port.in.concern;

import java.util.UUID;

public interface DeleteConcernUseCase {
  void execute(UUID id, String accountId);
}
