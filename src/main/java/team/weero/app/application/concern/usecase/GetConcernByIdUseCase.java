package team.weero.app.application.concern.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.concern.dto.response.ConcernResponse;
import team.weero.app.domain.answer.repository.AnswerRepository;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.domain.concern.repository.ConcernRepository;
import team.weero.app.domain.student.repository.StudentRepository;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetConcernByIdUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;
    private final AnswerRepository answerRepository;

    public GetConcernByIdUseCase(ConcernRepository concernRepository, StudentRepository studentRepository, AnswerRepository answerRepository) {
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
