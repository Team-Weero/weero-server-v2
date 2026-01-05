package team.weero.app.application.service.concern;
import team.weero.app.application.port.in.concern.CreateConcernUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.concern.dto.request.CreateConcernRequest;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.application.port.out.concern.ConcernRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.application.port.out.student.StudentRepository;

@Service
@Transactional
public class CreateConcernService implements CreateConcernUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;

    public CreateConcernService(ConcernRepository concernRepository, StudentRepository studentRepository) {
        this.concernRepository = concernRepository;
        this.studentRepository = studentRepository;
    }

    public void execute(CreateConcernRequest request, String accountId) {
        Student student = studentRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Concern concern = Concern.create(
                student.getId(),
                request.title(),
                request.contents()
        );

        concernRepository.save(concern);
    }
}
