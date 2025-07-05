package team.weero.app.persistence.counseling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.core.counseling.dto.request.CounselingRequest;
import team.weero.app.core.counseling.spi.CommandCounselingPort;
import team.weero.app.core.counseling.spi.QueryCounselingPort;
import team.weero.app.persistence.counseling.entity.CounselingApplication;
import team.weero.app.persistence.counseling.repository.CounselingRepository;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.repository.StudentRepository;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.teacher.repository.TeacherRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CounselingPersistenceAdapter implements QueryCounselingPort, CommandCounselingPort {

    private final CounselingRepository counselingRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Optional<Student> findByStudenetId(UUID studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public Optional<Teacher> findByTeacherId(UUID teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Override
    public boolean existsByTeacherAndDateAndTime(Teacher teacher, LocalDate date, LocalTime time) {
        return counselingRepository.existsByTeacherAndCounselDateAndTime(teacher, date, time);
    }

    @Override
    public void save(CounselingApplication application) {
        counselingRepository.save(application);
    }
}
