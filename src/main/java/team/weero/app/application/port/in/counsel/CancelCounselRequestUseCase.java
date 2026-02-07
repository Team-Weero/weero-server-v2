package team.weero.app.application.port.in.counsel;

import java.util.UUID;

public interface CancelCounselRequestUseCase {
  void execute(UUID id, UUID studentId);
}
