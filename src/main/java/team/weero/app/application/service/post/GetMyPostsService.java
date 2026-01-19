package team.weero.app.application.service.post;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.post.dto.response.GetAllPostResponse;
import team.weero.app.adapter.in.web.post.dto.response.GetAllPostResponse.PostItem;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.post.GetMyPostsUseCase;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyPostsService implements GetMyPostsUseCase {

  private final GetPostPort getPostPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public GetAllPostResponse execute(UUID userId) {

    StudentInfo studentInfo =
        loadStudentPort.loadByUserId(userId).orElseThrow(StudentNotFoundException::new);

    List<Post> posts = getPostPort.getAllByStudentId(studentInfo.id());

    List<PostItem> postItems =
        posts.stream()
            .map(
                post ->
                    new PostItem(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getStudentName(),
                        post.getCreatedAt(),
                        post.getUpdatedAt()))
            .toList();

    return new GetAllPostResponse(postItems);
  }
}
