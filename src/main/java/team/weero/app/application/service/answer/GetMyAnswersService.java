package team.weero.app.application.answer.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.answer.dto.response.AnswerResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.answer.repository.AnswerRepository;
import team.weero.app.infrastructure.persistence.student.entity.StudentJpaEntity;
import team.weero.app.infrastructure.persistence.student.repository.StudentJpaRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetMyAnswersUseCase {

    private final AnswerRepository answerRepository;
    private final StudentJpaRepository studentJpaRepository;

    public GetMyAnswersUseCase(AnswerRepository answerRepository, StudentJpaRepository studentJpaRepository) {
        this.answerRepository = answerRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public List<AnswerResponse> execute(String accountId) {
        StudentJpaEntity student = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return answerRepository.findByStudentId(student.getId()).stream()
                .map(answer -> new AnswerResponse(
                        answer.getId(),
                        answer.getConcernId(),
                        answer.getContent(),
                        student.getName(),
                        student.getNickname(),
                        answer.getCreatedAt()
                ))
                .toList();
    }
}
