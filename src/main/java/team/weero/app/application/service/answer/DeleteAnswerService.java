package team.weero.app.application.service.answer;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.application.port.in.answer.DeleteAnswerUseCase;
import team.weero.app.application.port.out.answer.AnswerPort;
import team.weero.app.domain.answer.exception.UnauthorizedAccessException;
import team.weero.app.domain.answer.model.Answer;
import team.weero.app.domain.auth.exception.UserNotFoundException;

@Service
@Transactional
public class DeleteAnswerService implements DeleteAnswerUseCase {

  private final AnswerPort answerPort;
  private final StudentJpaRepository studentJpaRepository;

  public DeleteAnswerService(AnswerPort answerPort, StudentJpaRepository studentJpaRepository) {
    this.answerPort = answerPort;
    this.studentJpaRepository = studentJpaRepository;
  }

  public void execute(UUID answerId, String accountId) {
    StudentJpaEntity student =
        studentJpaRepository
            .findByAccountId(accountId)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

    Answer answer =
        answerPort.findById(answerId).orElseThrow(() -> new RuntimeException("답변을 찾을 수 없습니다"));

    if (!answer.isWrittenBy(student.getId())) {
      throw UnauthorizedAccessException.EXCEPTION;
    }

    answerPort.deleteById(answerId);
  }
}
