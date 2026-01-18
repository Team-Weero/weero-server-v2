package team.weero.app.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.application.port.out.SaveStudentPort;

@Component
@RequiredArgsConstructor
public class SaveStudentAdapter implements SaveStudentPort {

  private final StudentRepository studentRepository;

  @Override
  public void save(StudentJpaEntity student) {
    studentRepository.save(student);
  }
}
