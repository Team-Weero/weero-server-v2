package team.weero.app.core.answer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.core.answer.dto.request.CreateAnswerRequest;
import team.weero.app.core.answer.dto.response.AnswerResponse;
import team.weero.app.core.answer.exception.UnauthorizedAccessException;
import team.weero.app.core.answer.spi.AnswerPort;
import team.weero.app.core.auth.exception.UserNotFoundException;
import team.weero.app.core.concern.exception.ConcernNotFoundException;
import team.weero.app.core.concern.spi.ConcernPort;
import team.weero.app.persistence.answer.entity.Answer;
import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.repository.StudentRepository;
import team.weero.app.persistence.student.type.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerPort answerPort;
    private final ConcernPort concernPort;
    private final StudentRepository studentRepository;

    public AnswerServiceImpl(AnswerPort answerPort, ConcernPort concernPort, StudentRepository studentRepository) {
        this.answerPort = answerPort;
        this.concernPort = concernPort;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void createAnswer(CreateAnswerRequest request, String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (student.getStudentRole() != Role.AGENT) {
            throw UnauthorizedAccessException.EXCEPTION;
        }

        Concern concern = concernPort.findById(request.concernId())
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        if (concern.isResolved()) {
            throw new RuntimeException("이미 해결된 고민입니다");
        }

        Answer answer = Answer.builder()
                .concern(concern)
                .student(student)
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .build();

        answerPort.save(answer);
    }

    @Override
    public List<AnswerResponse> getAnswersByConcernId(UUID concernId) {
        Concern concern = concernPort.findById(concernId)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        return answerPort.findByConcern(concern).stream()
                .map(this::toAnswerResponse)
                .toList();
    }

    @Override
    public List<AnswerResponse> getMyAnswers(String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return answerPort.findByStudent(student).stream()
                .map(this::toAnswerResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteAnswer(UUID answerId, String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Answer answer = answerPort.findById(answerId)
                .orElseThrow(() -> new RuntimeException("답변을 찾을 수 없습니다"));

        if (!answer.getStudent().getId().equals(student.getId())) {
            throw UnauthorizedAccessException.EXCEPTION;
        }

        answerPort.deleteById(answerId);
    }

    private AnswerResponse toAnswerResponse(Answer answer) {
        return new AnswerResponse(
                answer.getId(),
                answer.getConcern().getId(),
                answer.getContent(),
                answer.getStudent().getName(),
                answer.getStudent().getNickname(),
                answer.getCreatedAt()
        );
    }
}
