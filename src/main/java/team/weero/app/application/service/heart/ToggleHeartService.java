package team.weero.app.application.service.heart;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.heart.ToggleHeartUseCase;
import team.weero.app.application.port.out.heart.HeartPort;

@Service
@RequiredArgsConstructor
public class ToggleHeartService implements ToggleHeartUseCase {

  private final HeartPort heartPort;

  @Override
  @Transactional
  public void execute(UUID postId, UUID userId) {
    if (heartPort.exists(postId, userId)) {
      heartPort.delete(postId, userId);
    } else {
      heartPort.save(postId, userId);
    }
  }
}
