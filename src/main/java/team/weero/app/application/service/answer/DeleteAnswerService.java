package team.weero.app.application.answer.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.answer.exception.UnauthorizedAccessException;
import team.weero.app.domain.answer.model.Answer;
import team.weero.app.domain.answer.repository.AnswerRepository;
import team.weero.app.infrastructure.persistence.student.entity.StudentJpaEntity;
import team.weero.app.infrastructure.persistence.student.repository.StudentJpaRepository;

import java.util.UUID;

@Service
@Transactional
public class DeleteAnswerUseCase {

    private final AnswerRepository answerRepository;
    private final StudentJpaRepository studentJpaRepository;

    public DeleteAnswerUseCase(AnswerRepository answerRepository, StudentJpaRepository studentJpaRepository) {
        this.answerRepository = answerRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public void execute(UUID answerId, String accountId) {
        StudentJpaEntity student = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("답변을 찾을 수 없습니다"));

        if (!answer.isWrittenBy(student.getId())) {
            throw UnauthorizedAccessException.EXCEPTION;
        }

        answerRepository.deleteById(answerId);
    }
}
