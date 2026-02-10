package team.weero.app.application.port.in.heart;

import java.util.UUID;

public interface ToggleHeartUseCase {
  void execute(UUID postId, UUID userId);
}
