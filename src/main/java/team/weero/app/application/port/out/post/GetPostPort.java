package team.weero.app.application.port.out.post;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.domain.post.model.Post;

public interface GetPostPort {
  Optional<Post> getById(UUID postId);

  Page<Post> getAll(Pageable pageable);

  Page<Post> getAllByStudentId(UUID studentId, Pageable pageable);
}
