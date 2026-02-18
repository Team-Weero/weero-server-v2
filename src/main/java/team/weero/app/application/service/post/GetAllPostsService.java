package team.weero.app.application.service.post;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.post.GetAllPostUseCase;
import team.weero.app.application.port.in.post.dto.response.GetAllPostInfo;
import team.weero.app.application.port.out.heart.PostHeartPort;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllPostsService implements GetAllPostUseCase {

  private final GetPostPort getPostPort;
  private final PostHeartPort postHeartPort;

  @Override
  public GetAllPostInfo execute(UUID userId) {

    List<Post> posts = getPostPort.getAll();

    List<GetAllPostInfo.PostInfo> postItems =
        posts.stream()
            .map(
                post -> {
                  boolean hearted = postHeartPort.exists(post.getId(), userId);
                  int heartCount = postHeartPort.countByPostId(post.getId());
                  return new GetAllPostInfo.PostInfo(
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

    return new GetAllPostInfo(postItems);
  }
}
