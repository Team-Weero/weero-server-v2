package team.weero.app.application.port.in.post;

import java.util.UUID;
import team.weero.app.application.port.in.post.dto.response.GetPostInfo;

public interface GetPostUseCase {
  GetPostInfo execute(UUID userId, UUID postId);
}
