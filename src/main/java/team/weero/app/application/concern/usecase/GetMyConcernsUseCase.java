package team.weero.app.application.concern.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.concern.dto.response.ConcernResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.answer.repository.AnswerRepository;
import team.weero.app.domain.concern.repository.ConcernRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.student.repository.StudentRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetMyConcernsUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;
    private final AnswerRepository answerRepository;

    public GetMyConcernsUseCase(ConcernRepository concernRepository, StudentRepository studentRepository, AnswerRepository answerRepository) {
        this.concernRepository = concernRepository;
        this.studentRepository = studentRepository;
        this.answerRepository = answerRepository;
    }

    public List<ConcernResponse> execute(String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return concernRepository.findByStudentId(student.getId()).stream()
                .map(concern -> {
                    int answerCount = answerRepository.countByConcernId(concern.getId());
                    return ConcernResponse.from(concern, student, answerCount);
                })
                .toList();
    }
}
