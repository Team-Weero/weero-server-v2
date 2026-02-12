package team.weero.app.adapter.out.post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.post.mapper.PostMapper;
import team.weero.app.adapter.out.post.repository.PostRepository;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.application.exception.post.ForbiddenPostAccessException;
import team.weero.app.application.exception.post.PostNotFoundException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.out.post.*;
import team.weero.app.domain.post.model.Post;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter
    implements GetPostPort, SavePostPort, LoadPostPort, UpdatePostPort, IncrementViewCountPort {

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final StudentRepository studentRepository;

  @Override
  public Optional<Post> getById(UUID postId) {
    return postRepository.findByIdAndDeletedAtIsNull(postId).map(postMapper::toDomain);
  }

  @Override
  public List<Post> getAll() {
    return postRepository.findAllByDeletedAtIsNullOrderByCreatedAtDesc().stream()
        .map(postMapper::toDomain)
        .toList();
  }

  @Override
  public List<Post> getAllByStudentId(UUID studentId) {
    return postRepository
        .findAllByStudentIdAndDeletedAtIsNullOrderByCreatedAtDesc(studentId)
        .stream()
        .map(postMapper::toDomain)
        .toList();
  }

  @Override
  public Optional<PostJpaEntity> loadById(UUID postId) {
    return postRepository.findById(postId);
  }

  @Override
  public Post save(Post post) {
    StudentJpaEntity student =
        studentRepository
            .findById(post.getStudentId())
            .orElseThrow(() -> StudentNotFoundException.INSTANCE);

    PostJpaEntity entity = PostMapper.toEntity(post, student);
    PostJpaEntity savedEntity = postRepository.save(entity);
    return postMapper.toDomain(savedEntity);
  }

  @Override
  public void incrementViewCount(UUID postId) {
    postRepository.incrementViewCount(postId);
  }

  @Override
  public void update(UUID postId, UUID userId, String title, String content) {
    PostJpaEntity post =
        postRepository
            .findByIdAndDeletedAtIsNull(postId)
            .orElseThrow(() -> PostNotFoundException.INSTANCE);

    if (!post.getStudent().getUser().getId().equals(userId)) {
      throw ForbiddenPostAccessException.INSTANCE;
    }

    post.update(title, content);

    postRepository.save(post);
  }
}
