package team.weero.app.application.port.in.post;

import java.util.UUID;
import team.weero.app.application.port.in.post.dto.response.GetAllPostInfo;

public interface GetMyPostsUseCase {
  GetAllPostInfo execute(UUID userId);
}
