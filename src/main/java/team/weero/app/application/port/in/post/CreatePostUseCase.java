package team.weero.app.application.port.in.post;

import team.weero.app.application.port.in.post.dto.request.CreatePostCommand;

public interface CreatePostUseCase {

  void execute(CreatePostCommand command);
}
