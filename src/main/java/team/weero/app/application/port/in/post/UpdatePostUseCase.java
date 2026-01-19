package team.weero.app.application.port.in.post;

import java.util.UUID;
import team.weero.app.adapter.in.web.post.dto.request.UpdatePostRequest;

public interface UpdatePostUseCase {
  void execute(UUID postId, UpdatePostRequest request);
}
