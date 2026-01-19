package team.weero.app.application.port.out.post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.post.model.Post;

public interface GetPostPort {
  Optional<Post> getById(UUID postId);

  List<Post> getAll();

  List<Post> getAllByStudentId(UUID studentId);
}
