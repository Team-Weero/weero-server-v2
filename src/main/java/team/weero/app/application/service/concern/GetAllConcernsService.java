package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.GetAllConcernsUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;
import team.weero.app.application.port.out.answer.AnswerPort;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.application.port.out.student.StudentPort;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAllConcernsService implements GetAllConcernsUseCase {

    private final ConcernPort concernPort;
    private final StudentPort studentPort;
    private final AnswerPort answerPort;

    public GetAllConcernsService(ConcernPort concernPort, StudentPort studentPort, AnswerPort answerPort) {
        this.concernPort = concernPort;
        this.studentPort = studentPort;
        this.answerPort = answerPort;
    }

    public List<ConcernResponse> execute() {
        return concernPort.findAll().stream()
                .map(concern -> {
                    var student = studentPort.findById(concern.getStudentId()).orElse(null);
                    int answerCount = answerPort.countByConcernId(concern.getId());
                    return ConcernResponse.from(concern, student, answerCount);
                })
                .toList();
    }
}
