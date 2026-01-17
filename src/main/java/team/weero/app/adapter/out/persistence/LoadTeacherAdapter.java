package team.weero.app.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.application.port.in.TeacherInfo;
import team.weero.app.application.port.out.LoadTeacherPort;

@Component
@RequiredArgsConstructor
public class LoadTeacherAdapter implements LoadTeacherPort {

  private final TeacherRepository teacherRepository;

  @Override
  public Optional<TeacherInfo> loadByUserId(UUID userId) {
    return teacherRepository.findByUserId(userId).map(this::toTeacherInfo);
  }

  private TeacherInfo toTeacherInfo(TeacherJpaEntity entity) {
    return new TeacherInfo(entity.getId(), entity.getName());
  }
}
