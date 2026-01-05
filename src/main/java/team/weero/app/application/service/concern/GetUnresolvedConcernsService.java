package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.GetUnresolvedConcernsUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;
import team.weero.app.application.port.out.answer.AnswerRepository;
import team.weero.app.application.port.out.concern.ConcernRepository;
import team.weero.app.application.port.out.student.StudentRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetUnresolvedConcernsService implements GetUnresolvedConcernsUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;
    private final AnswerRepository answerRepository;

    public GetUnresolvedConcernsService(ConcernRepository concernRepository, StudentRepository studentRepository, AnswerRepository answerRepository) {
        this.concernRepository = concernRepository;
        this.studentRepository = studentRepository;
        this.answerRepository = answerRepository;
    }

    public List<ConcernResponse> execute() {
        return concernRepository.findByIsResolved(false).stream()
                .map(concern -> {
                    var student = studentRepository.findById(concern.getStudentId()).orElse(null);
                    int answerCount = answerRepository.countByConcernId(concern.getId());
                    return ConcernResponse.from(concern, student, answerCount);
                })
                .toList();
    }
}
