package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.GetMyConcernsUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.application.port.out.answer.AnswerPort;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.domain.student.model.Student;
import team.weero.app.application.port.out.student.StudentPort;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetMyConcernsService implements GetMyConcernsUseCase {

    private final ConcernPort concernPort;
    private final StudentPort studentPort;
    private final AnswerPort answerPort;

    public GetMyConcernsService(ConcernPort concernPort, StudentPort studentPort, AnswerPort answerPort) {
        this.concernPort = concernPort;
        this.studentPort = studentPort;
        this.answerPort = answerPort;
    }

    public List<ConcernResponse> execute(String accountId) {
        Student student = studentPort.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return concernPort.findByStudentId(student.getId()).stream()
                .map(concern -> {
                    int answerCount = answerPort.countByConcernId(concern.getId());
                    return ConcernResponse.from(concern, student, answerCount);
                })
                .toList();
    }
}
