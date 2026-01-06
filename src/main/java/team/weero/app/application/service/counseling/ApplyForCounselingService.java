package team.weero.app.application.service.counseling;
import team.weero.app.application.port.in.counseling.ApplyForCounselingUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.counseling.dto.request.CounselingRequest;
import team.weero.app.domain.counseling.exception.DuplicateReservationException;
import team.weero.app.domain.counseling.exception.NotFoundException;
import team.weero.app.domain.counseling.model.CounselingApplication;
import team.weero.app.application.port.out.counseling.CounselingRepository;
import team.weero.app.application.port.out.student.StudentRepository;
import team.weero.app.application.port.out.teacher.TeacherRepository;

@Service
@RequiredArgsConstructor
public class ApplyForCounselingService implements ApplyForCounselingUseCase {

    private final CounselingRepository counselingRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public void execute(CounselingRequest request) {
        studentRepository.findById(request.studentId())
                .orElseThrow(NotFoundException::new);

        teacherRepository.findById(request.teacherId())
                .orElseThrow(NotFoundException::new);

        boolean exists = counselingRepository.existsByTeacherIdAndCounselDateAndTime(
                request.teacherId(),
                request.date(),
                request.time()
        );
        if (exists) {
            throw new DuplicateReservationException();
        }

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
