package team.weero.app.application.port.in.post;

import java.util.UUID;
import team.weero.app.adapter.in.post.dto.request.CreatePostRequest;

public interface CreatePostUseCase {

  void execute(CreatePostRequest request, UUID userId);
}
