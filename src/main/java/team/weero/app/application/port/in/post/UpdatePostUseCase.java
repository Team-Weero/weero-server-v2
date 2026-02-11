package team.weero.app.application.port.in.post;

import team.weero.app.application.port.in.post.dto.request.UpdatePostCommand;

public interface UpdatePostUseCase {
  void execute(UpdatePostCommand command);
}
