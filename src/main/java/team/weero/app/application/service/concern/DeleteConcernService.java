package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.DeleteConcernUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.concern.exception.ConcernNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.application.port.out.student.StudentRepository;

import java.util.UUID;

@Service
@Transactional
public class DeleteConcernService implements DeleteConcernUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;

    public DeleteConcernService(ConcernRepository concernRepository, StudentRepository studentRepository) {
        this.concernRepository = concernRepository;
        this.studentRepository = studentRepository;
    }

    public void execute(UUID id, String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> ConcernNotFoundException.EXCEPTION);

        if (!concern.isOwnedBy(student.getId())) {
            throw new RuntimeException("본인의 고민만 삭제할 수 있습니다");
        }

        concernRepository.deleteById(id);
    }
}
