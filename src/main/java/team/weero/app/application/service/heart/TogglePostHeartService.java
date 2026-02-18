package team.weero.app.application.service.heart;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.heart.TogglePostHeartUseCase;
import team.weero.app.application.port.out.heart.PostHeartPort;

@Service
@RequiredArgsConstructor
public class TogglePostHeartService implements TogglePostHeartUseCase {

  private final PostHeartPort postHeartPort;

  @Override
  @Transactional
  public void execute(UUID postId, UUID userId) {
    if (postHeartPort.exists(postId, userId)) {
      postHeartPort.delete(postId, userId);
    } else {
      postHeartPort.save(postId, userId);
    }
  }
}
