package team.weero.app.core.concern.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.core.auth.exception.UserNotFoundException;
import team.weero.app.core.concern.dto.request.CreateConcernRequest;
import team.weero.app.core.concern.dto.response.ConcernResponse;
import team.weero.app.core.concern.exception.ConcernNotFoundException;
import team.weero.app.core.concern.spi.ConcernPort;
import team.weero.app.core.answer.spi.AnswerPort;
import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ConcernServiceImpl implements ConcernService {

    private final ConcernPort concernPort;
    private final AnswerPort answerPort;
    private final StudentRepository studentRepository;

    public ConcernServiceImpl(ConcernPort concernPort, AnswerPort answerPort, StudentRepository studentRepository) {
        this.concernPort = concernPort;
        this.answerPort = answerPort;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void createConcern(CreateConcernRequest request, String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Concern concern = Concern.builder()
                .title(request.title())
                .contents(request.contents())
                .student(student)
                .createdAt(LocalDateTime.now())
                .isResolved(false)
                .build();

        concernPort.save(concern);
    }

    @Override
    public ConcernResponse getConcernById(UUID id) {
        Concern concern = concernPort.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        int answerCount = answerPort.countByConcern(concern);
        
        return new ConcernResponse(
                concern.getId(),
                concern.getTitle(),
                concern.getContents(),
                concern.getStudent().getName(),
                concern.getStudent().getNickname(),
                concern.getCreatedAt(),
                concern.isResolved(),
                answerCount
        );
    }

    @Override
    public List<ConcernResponse> getMyConcerns(String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return concernPort.findByStudent(student).stream()
                .map(this::toConcernResponse)
                .toList();
    }

    @Override
    public List<ConcernResponse> getAllConcerns() {
        return concernPort.findAll().stream()
                .map(this::toConcernResponse)
                .toList();
    }

    @Override
    public List<ConcernResponse> getUnresolvedConcerns() {
        return concernPort.findByIsResolved(false).stream()
                .map(this::toConcernResponse)
                .toList();
    }

    @Override
    @Transactional
    public void resolveConcern(UUID id) {
        Concern concern = concernPort.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        concern.resolve();
        concernPort.save(concern);
    }

    @Override
    @Transactional
    public void deleteConcern(UUID id, String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Concern concern = concernPort.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        if (!concern.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("본인의 고민만 삭제할 수 있습니다");
        }

        concernPort.deleteById(id);
    }

    private ConcernResponse toConcernResponse(Concern concern) {
        int answerCount = answerPort.countByConcern(concern);
        
        return new ConcernResponse(
                concern.getId(),
                concern.getTitle(),
                concern.getContents(),
                concern.getStudent().getName(),
                concern.getStudent().getNickname(),
                concern.getCreatedAt(),
                concern.isResolved(),
                answerCount
        );
    }
}
