package team.weero.app.application.port.in.post;

import java.util.UUID;

public interface DecreaseHeartUseCase {
  void execute(UUID postId, UUID userId);
}
