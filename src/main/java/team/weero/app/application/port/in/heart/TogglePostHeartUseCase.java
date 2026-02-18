package team.weero.app.application.port.in.heart;

import java.util.UUID;

public interface TogglePostHeartUseCase {
  void execute(UUID postId, UUID userId);
}
