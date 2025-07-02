package team.weero.app.core.counseling.spi;

import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface QueryCounselingPort {

    Optional<Student> findByStudenetId(UUID studenetId);

    Optional<Teacher> findByTeacherId(UUID teacherId);

    boolean existsByTeacherAndDateAndTime(Teacher teacher, LocalDate date, LocalTime time);
}
