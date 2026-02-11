package team.weero.app.application.service.post;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.post.dto.response.GetAllPostResponse;
import team.weero.app.adapter.in.web.post.dto.response.GetAllPostResponse.PostItem;
import team.weero.app.application.port.in.post.GetAllPostUseCase;
import team.weero.app.application.port.out.heart.HeartPort;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllPostsService implements GetAllPostUseCase {

  private final GetPostPort getPostPort;
  private final HeartPort heartPort;

  @Override
  public GetAllPostResponse execute(UUID userId) {

    List<Post> posts = getPostPort.getAll();

    List<PostItem> postItems =
        posts.stream()
            .map(
                post -> {
                  boolean hearted = heartPort.exists(post.getId(), userId);
                  int heartCount = heartPort.countByPostId(post.getId());
                  return new PostItem(
                      post.getId(),
                      post.getTitle(),
                      post.getNickName(),
                      post.getViewCount(),
                      heartCount,
                      hearted,
                      post.getCreatedAt(),
                      post.getUpdatedAt());
                })
            .toList();

    return new GetAllPostResponse(postItems);
  }
}
