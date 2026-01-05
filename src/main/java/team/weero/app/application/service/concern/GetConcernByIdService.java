package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.GetConcernByIdUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;
import team.weero.app.application.port.out.answer.AnswerRepository;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernRepository;
import team.weero.app.application.port.out.student.StudentRepository;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetConcernByIdService implements GetConcernByIdUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;
    private final AnswerRepository answerRepository;

    public GetConcernByIdService(ConcernRepository concernRepository, StudentRepository studentRepository, AnswerRepository answerRepository) {
        this.concernRepository = concernRepository;
        this.studentRepository = studentRepository;
        this.answerRepository = answerRepository;
    }

    public ConcernResponse execute(UUID id) {
        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        var student = studentRepository.findById(concern.getStudentId()).orElse(null);
        int answerCount = answerRepository.countByConcernId(concern.getId());

        return ConcernResponse.from(concern, student, answerCount);
    }
}
