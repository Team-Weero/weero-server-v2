package team.weero.app.application.service.post;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.post.dto.response.GetPostResponse;
import team.weero.app.application.exception.post.PostNotFoundException;
import team.weero.app.application.port.in.post.GetPostUseCase;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetPostService implements GetPostUseCase {

  private final GetPostPort getPostPort;

  @Override
  public GetPostResponse execute(UUID postId) {

    Post post = getPostPort.getById(postId).orElseThrow(PostNotFoundException::new);

    if (post.isDeleted()) {
      throw new PostNotFoundException();
    }

    return new GetPostResponse(
        post.getId(),
        post.getTitle(),
        post.getContent(),
        post.getStudentName(),
        post.getCreatedAt(),
        post.getUpdatedAt());
  }
}
