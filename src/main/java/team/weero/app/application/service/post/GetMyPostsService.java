package team.weero.app.application.service.post;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.post.GetMyPostsUseCase;
import team.weero.app.application.port.in.post.dto.response.PagedPostInfo;
import team.weero.app.application.port.out.heart.PostHeartPort;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyPostsService implements GetMyPostsUseCase {

  private final GetPostPort getPostPort;
  private final LoadStudentPort loadStudentPort;
  private final PostHeartPort postHeartPort;

  @Override
  public PagedPostInfo execute(UUID userId, int page, int size) {
    var studentInfo =
        loadStudentPort.loadByUserId(userId).orElseThrow(() -> StudentNotFoundException.INSTANCE);

    Pageable pageable =
        PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("id")));

    Page<Post> postPage = getPostPort.getAllByStudentId(studentInfo.id(), pageable);

    List<PagedPostInfo.PostInfo> postItems =
        postPage.getContent().stream()
            .map(
                post ->
                    new PagedPostInfo.PostInfo(
                        post.getId(),
                        post.getTitle(),
                        post.getNickName(),
                        post.getViewCount(),
                        postHeartPort.countByPostId(post.getId()),
                        postHeartPort.exists(post.getId(), userId),
                        post.getCreatedAt(),
                        post.getUpdatedAt()))
            .toList();

    return new PagedPostInfo(
        postItems,
        postPage.getNumber(),
        postPage.getSize(),
        postPage.getTotalElements(),
        postPage.getTotalPages(),
        postPage.hasNext());
  }
}
