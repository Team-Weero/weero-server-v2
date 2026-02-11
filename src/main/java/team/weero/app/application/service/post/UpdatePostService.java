package team.weero.app.application.service.post;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.post.UpdatePostUseCase;
import team.weero.app.application.port.in.post.dto.request.UpdatePostCommand;
import team.weero.app.application.port.out.post.UpdatePostPort;
import team.weero.app.application.port.out.student.LoadStudentPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdatePostService implements UpdatePostUseCase {

  private final UpdatePostPort updatePostPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public void execute(UpdatePostCommand command) {
    loadStudentPort
        .loadByUserId(command.userId())
        .orElseThrow(() -> StudentNotFoundException.INSTANCE);

    updatePostPort.update(command.postId(), command.userId(), command.title(), command.content());
  }
}
