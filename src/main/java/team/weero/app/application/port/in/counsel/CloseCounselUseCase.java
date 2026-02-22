package team.weero.app.application.port.in.counsel;

import java.util.UUID;

public interface CloseCounselUseCase {
  void execute(UUID chatRoomId, UUID userId);
}
