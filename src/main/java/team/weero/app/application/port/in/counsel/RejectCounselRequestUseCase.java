package team.weero.app.application.port.in.counsel;

import java.util.UUID;

public interface RejectCounselRequestUseCase {
  void execute(UUID id, UUID teacherId);
}
