package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.CreateConcernUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.concern.dto.request.CreateConcernRequest;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.domain.student.model.Student;
import team.weero.app.application.port.out.student.StudentPort;

@Service
@Transactional
public class CreateConcernService implements CreateConcernUseCase {

    private final ConcernPort concernPort;
    private final StudentPort studentPort;

    public CreateConcernService(ConcernPort concernPort, StudentPort studentPort) {
        this.concernPort = concernPort;
        this.studentPort = studentPort;
    }

    public void execute(CreateConcernRequest request, String accountId) {
        Student student = studentPort.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Concern concern = Concern.create(
                student.getId(),
                request.title(),
                request.contents()
        );

        concernPort.save(concern);
    }
}
