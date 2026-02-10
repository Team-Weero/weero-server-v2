package team.weero.app.application.service.post;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.post.dto.response.GetPostResponse;
import team.weero.app.application.exception.post.PostNotFoundException;
import team.weero.app.application.port.in.post.GetPostUseCase;
import team.weero.app.application.port.out.heart.HeartPort;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.application.port.out.post.SavePostPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional
public class GetPostService implements GetPostUseCase {

  private final GetPostPort getPostPort;
  private final SavePostPort savePostPort;
  private final HeartPort heartPort;

  @Override
  public GetPostResponse execute(UUID postId, UUID userId) {

    Post post = getPostPort.getById(postId).orElseThrow(() -> PostNotFoundException.INSTANCE);

    if (post.isDeleted()) {
      throw PostNotFoundException.INSTANCE;
    }

    post.increaseViewCount();
    Post updatedPost = savePostPort.save(post);

    boolean hearted = heartPort.exists(postId, userId);
    int heartCount = heartPort.countByPostId(postId);

    return new GetPostResponse(
            updatedPost.getId(),
            updatedPost.getTitle(),
            updatedPost.getContent(),
            updatedPost.getNickName(),
            updatedPost.getViewCount(),
            heartCount,
            hearted,
            updatedPost.getCreatedAt(),
            updatedPost.getUpdatedAt());
  }
}
