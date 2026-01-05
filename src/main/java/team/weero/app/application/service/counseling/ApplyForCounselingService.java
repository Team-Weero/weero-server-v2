package team.weero.app.application.counseling.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.counseling.dto.request.CounselingRequest;
import team.weero.app.domain.counseling.exception.DuplicateReservationException;
import team.weero.app.domain.counseling.exception.NotFoundException;
import team.weero.app.domain.counseling.model.CounselingApplication;
import team.weero.app.domain.counseling.repository.CounselingRepository;
import team.weero.app.domain.student.repository.StudentRepository;
import team.weero.app.domain.teacher.repository.TeacherRepository;

@Service
@RequiredArgsConstructor
public class ApplyForCounselingUseCase {

    private final CounselingRepository counselingRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public void execute(CounselingRequest request) {
        // Validate student exists
        studentRepository.findById(request.studentId())
                .orElseThrow(NotFoundException::new);

        // Validate teacher exists
        teacherRepository.findById(request.teacherId())
                .orElseThrow(NotFoundException::new);

        // Check for duplicate reservation
        boolean exists = counselingRepository.existsByTeacherIdAndCounselDateAndTime(
                request.teacherId(),
                request.date(),
                request.time()
        );
        if (exists) {
            throw new DuplicateReservationException();
        }

        // Create and save counseling application
        CounselingApplication counseling = CounselingApplication.create(
                request.studentId(),
                request.teacherId(),
                request.time(),
                request.date(),
                request.location()
        );

        counselingRepository.save(counseling);
    }
}
