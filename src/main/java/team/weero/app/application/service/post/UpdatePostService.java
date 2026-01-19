package team.weero.app.application.service.post;

import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.adapter.in.web.post.dto.request.UpdatePostRequest;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.post.UpdatePostUseCase;
import team.weero.app.application.port.out.post.UpdatePostPort;
import team.weero.app.application.port.out.student.LoadStudentPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdatePostService implements UpdatePostUseCase {

  private final UpdatePostPort updatePostPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public void execute(UUID postId, UUID userId, UpdatePostRequest request) {
    loadStudentPort.loadByUserId(userId)
            .orElseThrow(StudentNotFoundException::new);

    updatePostPort.update(postId, userId, request.title(), request.content());
  }
}
