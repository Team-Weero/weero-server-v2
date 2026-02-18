package team.weero.app.application.port.in.heart;

import java.util.UUID;

public interface ToggleAnswerHeartUseCase {
  void execute(UUID answerId, UUID userId);
}
