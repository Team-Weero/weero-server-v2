package team.weero.app.application.port.out.post;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;

public interface LoadPostEntityPort {
  Optional<PostJpaEntity> loadEntityById(UUID postId);
}
