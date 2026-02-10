package team.weero.app.application.port.out.heart;

import java.util.UUID;

public interface HeartPort {
  boolean exists(UUID postId, UUID userId);

  void save(UUID postId, UUID userId);

  void delete(UUID postId, UUID userId);

  int countByPostId(UUID postId);
}
