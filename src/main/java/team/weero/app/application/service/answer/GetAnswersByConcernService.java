package team.weero.app.application.service.answer;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.application.port.in.answer.GetAnswersByConcernUseCase;
import team.weero.app.application.port.out.answer.AnswerPort;
import team.weero.app.application.port.out.concern.ConcernPort;
import team.weero.app.application.service.answer.dto.response.AnswerResponse;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;

@Service
@Transactional(readOnly = true)
public class GetAnswersByConcernService implements GetAnswersByConcernUseCase {

  private final AnswerPort answerPort;
  private final ConcernPort concernPort;
  private final StudentJpaRepository studentJpaRepository;

  public GetAnswersByConcernService(
      AnswerPort answerPort, ConcernPort concernPort, StudentJpaRepository studentJpaRepository) {
    this.answerPort = answerPort;
    this.concernPort = concernPort;
    this.studentJpaRepository = studentJpaRepository;
  }

  public List<AnswerResponse> execute(UUID concernId) {
    Concern concern =
        concernPort.findById(concernId).orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

    return answerPort.findByConcernId(concernId).stream()
        .map(
            answer -> {
              StudentJpaEntity student =
                  studentJpaRepository
                      .findById(answer.getStudentId())
                      .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다"));
              return new AnswerResponse(
                  answer.getId(),
                  answer.getConcernId(),
                  answer.getContent(),
                  student.getName(),
                  student.getNickname(),
                  answer.getCreatedAt());
            })
        .toList();
  }
}
