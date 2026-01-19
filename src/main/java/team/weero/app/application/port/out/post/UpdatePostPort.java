package team.weero.app.application.port.out.post;

import java.util.UUID;

public interface UpdatePostPort {

  void update(UUID postId, UUID userId, String title, String content);
}
