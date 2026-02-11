package team.weero.app.application.port.out.post;

import java.util.UUID;

public interface IncrementViewCountPort {
  void incrementViewCount(UUID postId);
}
