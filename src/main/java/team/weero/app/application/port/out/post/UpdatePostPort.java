package team.weero.app.application.port.out.post;

import java.util.UUID;

public interface UpdatePostPort {

  void update(UUID postId, String title, String content);
}
