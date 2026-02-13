package team.weero.app.application.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.post.CreatePostUseCase;
import team.weero.app.application.port.in.post.dto.request.CreatePostCommand;
import team.weero.app.application.port.in.student.dto.response.StudentInfo;
import team.weero.app.application.port.out.post.SavePostPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatePostService implements CreatePostUseCase {

  private final SavePostPort savePostPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public void execute(CreatePostCommand command) {

    StudentInfo studentInfo =
        loadStudentPort
            .loadByUserId(command.userId())
            .orElseThrow(() -> StudentNotFoundException.INSTANCE);

    Post post =
        Post.builder()
            .title(command.title())
            .content(command.content())
            .studentId(studentInfo.id())
            .build();

    savePostPort.save(post);
  }
}
