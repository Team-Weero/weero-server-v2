package team.weero.app.application.port.in.post;

import java.util.UUID;
import team.weero.app.application.port.in.post.dto.response.GetAllPostInfo;

public interface GetAllPostUseCase {
  GetAllPostInfo execute(UUID userId);
}
