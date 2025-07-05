package team.weero.app.persistence.counseling.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.persistence.counseling.entity.CounselingApplication;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Repository
public interface CounselingRepository extends CrudRepository<CounselingApplication, UUID> {
    boolean existsByTeacherAndCounselDateAndTime(Teacher teacher, LocalDate counselDate, LocalTime time);
}
