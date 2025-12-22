package team.weero.app.application.concern.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.concern.dto.response.ConcernResponse;
import team.weero.app.domain.answer.repository.AnswerRepository;
import team.weero.app.domain.concern.repository.ConcernRepository;
import team.weero.app.domain.student.repository.StudentRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetUnresolvedConcernsUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;
    private final AnswerRepository answerRepository;

    public GetUnresolvedConcernsUseCase(ConcernRepository concernRepository, StudentRepository studentRepository, AnswerRepository answerRepository) {
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
