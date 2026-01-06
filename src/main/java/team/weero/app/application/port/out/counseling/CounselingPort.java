package team.weero.app.application.port.out.counseling;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.counseling.model.CounselingApplication;

public interface CounselingPort {

  CounselingApplication save(CounselingApplication counselingApplication);

  Optional<CounselingApplication> findById(UUID id);

  List<CounselingApplication> findByStudentId(UUID studentId);

  List<CounselingApplication> findByTeacherId(UUID teacherId);

  boolean existsByTeacherIdAndCounselDateAndTime(
      UUID teacherId, LocalDate counselDate, LocalTime time);

  void deleteById(UUID id);
}
