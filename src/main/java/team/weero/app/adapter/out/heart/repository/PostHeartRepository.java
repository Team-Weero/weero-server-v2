package team.weero.app.adapter.out.heart.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.heart.entity.PostHeartJpaEntity;

@Repository
public interface PostHeartRepository extends JpaRepository<PostHeartJpaEntity, UUID> {
  boolean existsByPostIdAndUserId(UUID postId, UUID userId);

  void deleteByPostIdAndUserId(UUID postId, UUID userId);

  int countByPostId(UUID postId);
}
