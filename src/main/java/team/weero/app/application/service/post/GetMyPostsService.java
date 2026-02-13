package team.weero.app.application.service.post;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.post.GetMyPostsUseCase;
import team.weero.app.application.port.in.post.dto.response.GetAllPostInfo;
import team.weero.app.application.port.out.heart.HeartPort;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.application.port.out.student.LoadStudentPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyPostsService implements GetMyPostsUseCase {

  private final GetPostPort getPostPort;
  private final LoadStudentPort loadStudentPort;
  private final HeartPort heartPort;

  @Override
  public GetAllPostInfo execute(UUID userId) {

    var studentInfo =
        loadStudentPort.loadByUserId(userId).orElseThrow(() -> StudentNotFoundException.INSTANCE);

    var posts = getPostPort.getAllByStudentId(studentInfo.id());

    var postItems =
        posts.stream()
            .map(
                post -> {
                  boolean hearted = heartPort.exists(post.getId(), userId);
                  int heartCount = heartPort.countByPostId(post.getId());

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
