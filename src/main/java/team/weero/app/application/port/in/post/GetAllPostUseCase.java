package team.weero.app.application.port.in.post;

import java.util.UUID;
import team.weero.app.application.port.in.post.dto.response.PagedPostInfo;

public interface GetAllPostUseCase {
  PagedPostInfo execute(UUID userId, int page, int size);
}
