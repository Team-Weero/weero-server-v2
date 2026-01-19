package team.weero.app.application.port.out.post;

import team.weero.app.domain.post.model.Post;

public interface SavePostPort {
  Post save(Post post);
}
