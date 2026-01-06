package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.GetConcernByIdUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;
import team.weero.app.application.port.out.answer.AnswerPort;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.application.port.out.student.StudentPort;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetConcernByIdService implements GetConcernByIdUseCase {

    private final ConcernPort concernPort;
    private final StudentPort studentPort;
    private final AnswerPort answerPort;

    public GetConcernByIdService(ConcernPort concernPort, StudentPort studentPort, AnswerPort answerPort) {
        this.concernPort = concernPort;
        this.studentPort = studentPort;
        this.answerPort = answerPort;
    }

    public ConcernResponse execute(UUID id) {
        Concern concern = concernPort.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        var student = studentPort.findById(concern.getStudentId()).orElse(null);
        int answerCount = answerPort.countByConcernId(concern.getId());

        return ConcernResponse.from(concern, student, answerCount);
    }
}
