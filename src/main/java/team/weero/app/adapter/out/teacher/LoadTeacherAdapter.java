package team.weero.app.adapter.out.teacher;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.in.web.teacher.dto.response.TeacherInfo;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;

@Component
@RequiredArgsConstructor
public class LoadTeacherAdapter implements LoadTeacherPort {

  private final TeacherRepository teacherRepository;

  @Override
  public Optional<TeacherInfo> loadByUserId(UUID userId) {
    return teacherRepository.findByUser_Id(userId).map(this::toTeacherInfo);
  }

  @Override
  public Optional<TeacherInfo> loadById(UUID id) {
    return teacherRepository.findById(id).map(this::toTeacherInfo);
  }

  private TeacherInfo toTeacherInfo(TeacherJpaEntity entity) {
    return new TeacherInfo(entity.getId(), entity.getName());
  }
}
