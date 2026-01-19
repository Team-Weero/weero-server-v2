package team.weero.app.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.application.port.out.student.LoadStudentPort;

@Component
@RequiredArgsConstructor
public class LoadStudentAdapter implements LoadStudentPort {

  private final StudentRepository studentRepository;

  @Override
  public Optional<StudentInfo> loadByUserId(UUID userId) {
    return studentRepository.findByUser_Id(userId).map(this::toStudentInfo);
  }

  private StudentInfo toStudentInfo(StudentJpaEntity entity) {
    return new StudentInfo(
        entity.getId(),
        entity.getName(),
        entity.getAccountId(),
        entity.getGrade(),
        entity.getClassRoom(),
        entity.getNumber(),
        entity.getRole());
  }
}
