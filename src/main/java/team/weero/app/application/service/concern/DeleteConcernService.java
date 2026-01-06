package team.weero.app.application.service.concern;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.concern.DeleteConcernUseCase;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.application.port.out.student.StudentPort;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.domain.student.model.Student;

@Service
@Transactional
public class DeleteConcernService implements DeleteConcernUseCase {

  private final ConcernPort concernPort;
  private final StudentPort studentPort;

  public DeleteConcernService(ConcernPort concernPort, StudentPort studentPort) {
    this.concernPort = concernPort;
    this.studentPort = studentPort;
  }

  public void execute(UUID id, String accountId) {
    Student student =
        studentPort.findByAccountId(accountId).orElseThrow(() -> UserNotFoundException.EXCEPTION);

    Concern concern =
        concernPort.findById(id).orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

    if (!concern.isOwnedBy(student.getId())) {
      throw new RuntimeException("본인의 고민만 삭제할 수 있습니다");
    }

    concernPort.deleteById(id);
  }
}
