package team.weero.app.application.service.answer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.answer.dto.request.CreateAnswerRequest;
import team.weero.app.application.port.in.answer.CreateAnswerUseCase;
import team.weero.app.application.port.out.answer.AnswerRepository;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.answer.exception.UnauthorizedAccessException;
import team.weero.app.domain.answer.model.Answer;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernRepository;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.student.entity.StudentRole;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class CreateAnswerService implements CreateAnswerUseCase {

    private final AnswerRepository answerRepository;
    private final ConcernRepository concernRepository;
    private final StudentJpaRepository studentJpaRepository;

    public CreateAnswerService(AnswerRepository answerRepository, ConcernRepository concernRepository, StudentJpaRepository studentJpaRepository) {
        this.answerRepository = answerRepository;
        this.concernRepository = concernRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public void execute(CreateAnswerRequest request, String accountId) {
        StudentJpaEntity student = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (student.getStudentRole() != StudentRole.AGENT) {
            throw UnauthorizedAccessException.EXCEPTION;
        }

        Concern concern = concernRepository.findById(request.concernId())
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        if (concern.isResolved()) {
            throw new RuntimeException("이미 해결된 고민입니다");
        }

        Answer answer = Answer.builder()
                .concernId(concern.getId())
                .studentId(student.getId())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .build();

        answerRepository.save(answer);
    }
}
