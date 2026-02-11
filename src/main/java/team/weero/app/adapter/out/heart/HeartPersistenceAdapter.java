package team.weero.app.adapter.out.heart;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.heart.entity.HeartJpaEntity;
import team.weero.app.adapter.out.heart.repository.HeartRepository;
import team.weero.app.application.port.out.heart.HeartPort;

@Component
@RequiredArgsConstructor
public class HeartPersistenceAdapter implements HeartPort {

  private final HeartRepository heartRepository;

  @Override
  public boolean exists(UUID postId, UUID userId) {
    return heartRepository.existsByPostIdAndUserId(postId, userId);
  }

  @Override
  public void save(UUID postId, UUID userId) {
    heartRepository.save(HeartJpaEntity.of(postId, userId));
  }

  @Override
  public void delete(UUID postId, UUID userId) {
    heartRepository.deleteByPostIdAndUserId(postId, userId);
  }

  @Override
  public int countByPostId(UUID postId) {
      return heartRepository.countByPostId(postId);
  }
}
