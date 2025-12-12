package team.weero.app.core.counseling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.core.counseling.dto.request.CounselingRequest;
import team.weero.app.core.counseling.exception.NotFoundException;
import team.weero.app.core.counseling.spi.QueryCounselingPort;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCounselingServiceImpl implements GetCounselingService {

    private final QueryCounselingPort queryCounselingPort;

    @Transactional(readOnly = true)
    @Override
    public Student findByStudent(UUID studentId) {
        return queryCounselingPort.findByStudenetId(studentId)
                .orElseThrow(() -> new NotFoundException());
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher findByTeacher(CounselingRequest request) {
        return queryCounselingPort.findByTeacherId(request.teacherId())
                .orElseThrow(() -> new NotFoundException());
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByTeacherAndDateAndTime(CounselingRequest request) {
        Teacher teacher = findByTeacher(request);
        return queryCounselingPort.existsByTeacherAndDateAndTime(
                teacher,
                request.date(),
                request.time()
        );
    }
}