package team.weero.app.application.concern.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.concern.dto.request.CreateConcernRequest;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.domain.concern.repository.ConcernRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.student.repository.StudentRepository;

@Service
@Transactional
public class CreateConcernUseCase {

    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;

    public CreateConcernUseCase(ConcernRepository concernRepository, StudentRepository studentRepository) {
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
