package team.weero.app.core.counseling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.core.counseling.dto.request.CounselingRequest;
import team.weero.app.core.counseling.exception.DuplicateReservationException;
import team.weero.app.core.counseling.spi.CommandCounselingPort;
import team.weero.app.persistence.counseling.entity.CounselingApplication;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommandCounselingServiceImpl implements CommandCounselingService {

    private final GetCounselingService getCounselingService;
    private final CommandCounselingPort commandCounselingPort;

    @Transactional
    public void applyForCounseling(CounselingRequest request, UUID studentId) {

        Student student = getCounselingService.findByStudent(studentId);
        Teacher teacher = getCounselingService.findByTeacher(request);

        boolean exists = getCounselingService.existsByTeacherAndDateAndTime(request);
        if (exists) {
            throw new DuplicateReservationException();
        }

        CounselingApplication counseling = new CounselingApplication();
        counseling.setStudent(student);
        counseling.setTeacher(teacher);
        counseling.setCounselDate(request.date());
        counseling.setLocation(request.location());
        counseling.setTime(request.time());
        counseling.setApplicationDate(LocalDate.now());
        counseling.setChecked(false);

        commandCounselingPort.save(counseling);
    }
}