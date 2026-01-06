package team.weero.app.application.service.counseling;
import team.weero.app.application.port.in.counseling.ApplyForCounselingUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.counseling.dto.request.CounselingRequest;
import team.weero.app.domain.counseling.exception.DuplicateReservationException;
import team.weero.app.domain.counseling.exception.NotFoundException;
import team.weero.app.domain.counseling.model.CounselingApplication;
import team.weero.app.application.port.out.counseling.CounselingPort;
import team.weero.app.application.port.out.student.StudentPort;
import team.weero.app.application.port.out.teacher.TeacherPort;

@Service
@RequiredArgsConstructor
public class ApplyForCounselingService implements ApplyForCounselingUseCase {

    private final CounselingPort counselingPort;
    private final StudentPort studentPort;
    private final TeacherPort teacherPort;

    @Transactional
    public void execute(CounselingRequest request) {
        studentPort.findById(request.studentId())
                .orElseThrow(NotFoundException::new);

        teacherPort.findById(request.teacherId())
                .orElseThrow(NotFoundException::new);

        boolean exists = counselingPort.existsByTeacherIdAndCounselDateAndTime(
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

        counselingPort.save(counseling);
    }
}
