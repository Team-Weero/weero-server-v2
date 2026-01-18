package team.weero.app.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.application.port.out.SaveTeacherPort;

@Component
@RequiredArgsConstructor
public class SaveTeacherAdapter implements SaveTeacherPort {

  private final TeacherRepository teacherRepository;

  @Override
  public void save(TeacherJpaEntity teacher) {
    teacherRepository.save(teacher);
  }
}
