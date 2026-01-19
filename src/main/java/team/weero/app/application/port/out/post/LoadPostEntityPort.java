package team.weero.app.application.port.out.post;

import team.weero.app.adapter.out.post.entity.PostJpaEntity;

import java.util.Optional;
import java.util.UUID;

public interface LoadPostEntityPort {
    Optional<PostJpaEntity> loadEntityById(UUID postId);
}
