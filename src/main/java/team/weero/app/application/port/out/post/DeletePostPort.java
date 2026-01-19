package team.weero.app.application.port.out.post;

import java.util.UUID;

public interface DeletePostPort {
  void softDelete(UUID postId, UUID userId);
}
