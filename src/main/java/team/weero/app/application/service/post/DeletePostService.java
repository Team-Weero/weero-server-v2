package team.weero.app.application.service.post;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.application.exception.post.ForbiddenPostAccessException;
import team.weero.app.application.exception.post.PostNotFoundException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.post.DeletePostUseCase;
import team.weero.app.application.port.out.post.LoadPostPort;
import team.weero.app.application.port.out.student.LoadStudentPort;

@Service
@RequiredArgsConstructor
@Transactional
public class DeletePostService implements DeletePostUseCase {

  private final LoadPostPort loadPostPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public void execute(UUID postId, UUID userId) {

    PostJpaEntity post = loadPostPort.loadById(postId).orElseThrow(PostNotFoundException::new);

    if (post.getDeletedAt() != null) {
      throw new PostNotFoundException();
    }

    StudentInfo studentInfo =
        loadStudentPort.loadByUserId(userId).orElseThrow(StudentNotFoundException::new);

    if (!post.getStudent().getId().equals(studentInfo.id())) {
      throw new ForbiddenPostAccessException();
    }

    post.markDeleted();
  }
}
