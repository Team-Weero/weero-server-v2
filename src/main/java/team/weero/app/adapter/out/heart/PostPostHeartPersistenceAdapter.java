package team.weero.app.adapter.out.heart;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.heart.entity.PostHeartJpaEntity;
import team.weero.app.adapter.out.heart.repository.PostHeartRepository;
import team.weero.app.application.port.out.heart.PostHeartPort;

@Component
@RequiredArgsConstructor
public class PostPostHeartPersistenceAdapter implements PostHeartPort {

  private final PostHeartRepository postHeartRepository;

  @Override
  public boolean exists(UUID postId, UUID userId) {
    return postHeartRepository.existsByPostIdAndUserId(postId, userId);
  }

  @Override
  public void save(UUID postId, UUID userId) {
    postHeartRepository.save(PostHeartJpaEntity.of(postId, userId));
  }

  @Override
  public void delete(UUID postId, UUID userId) {
    postHeartRepository.deleteByPostIdAndUserId(postId, userId);
  }

  @Override
  public int countByPostId(UUID postId) {
    return postHeartRepository.countByPostId(postId);
  }
}
