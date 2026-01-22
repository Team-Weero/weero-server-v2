package team.weero.app.application.service.answer;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.application.exception.answer.AnswerAccessDeniedException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.exception.user.UserNotFoundException;
import team.weero.app.application.port.in.answer.DeleteAnswerUseCase;
import team.weero.app.application.port.out.answer.DeleteAnswerPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.application.port.out.user.LoadUserPort;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.domain.student.type.StudentRole;
import team.weero.app.domain.user.model.User;

@Service
@RequiredArgsConstructor
public class DeleteAnswerService implements DeleteAnswerUseCase {

  private final DeleteAnswerPort deleteAnswerPort;
  private final LoadStudentPort loadStudentPort;
  private final LoadUserPort loadUserPort;

  @Override
  public void execute(UUID userId, UUID answerId) {

    User user = loadUserPort.loadById(userId)
            .orElseThrow(() -> UserNotFoundException.INSTANCE);

    if (user.getAuthority() == Authority.TEACHER) {
      deleteAnswerPort.delete(answerId);
      return;
    }

    StudentInfo student = loadStudentPort.loadByUserId(userId)
            .orElseThrow(() -> StudentNotFoundException.INSTANCE);

    if (student.role() == StudentRole.AGENT) {
      deleteAnswerPort.delete(answerId);
      return;
    }

    throw AnswerAccessDeniedException.INSTANCE;
  }
}
