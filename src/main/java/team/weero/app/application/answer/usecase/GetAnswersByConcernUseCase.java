package team.weero.app.application.answer.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.answer.dto.response.AnswerResponse;
import team.weero.app.domain.answer.repository.AnswerRepository;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.domain.concern.repository.ConcernRepository;
import team.weero.app.infrastructure.persistence.student.entity.StudentJpaEntity;
import team.weero.app.infrastructure.persistence.student.repository.StudentJpaRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetAnswersByConcernUseCase {

    private final AnswerRepository answerRepository;
    private final ConcernRepository concernRepository;
    private final StudentJpaRepository studentJpaRepository;

    public GetAnswersByConcernUseCase(AnswerRepository answerRepository, ConcernRepository concernRepository, StudentJpaRepository studentJpaRepository) {
        this.answerRepository = answerRepository;
        this.concernRepository = concernRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public List<AnswerResponse> execute(UUID concernId) {
        Concern concern = concernRepository.findById(concernId)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        return answerRepository.findByConcernId(concernId).stream()
                .map(answer -> {
                    StudentJpaEntity student = studentJpaRepository.findById(answer.getStudentId())
                            .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다"));
                    return new AnswerResponse(
                            answer.getId(),
                            answer.getConcernId(),
                            answer.getContent(),
                            student.getName(),
                            student.getNickname(),
                            answer.getCreatedAt()
                    );
                })
                .toList();
    }
}
