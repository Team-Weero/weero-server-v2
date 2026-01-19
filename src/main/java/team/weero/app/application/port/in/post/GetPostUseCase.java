package team.weero.app.application.port.in.post;

import java.util.UUID;
import team.weero.app.adapter.in.web.post.dto.response.GetPostResponse;

public interface GetPostUseCase {
  GetPostResponse execute(UUID postId);
}
