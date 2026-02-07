package team.weero.app.application.port.out.teacher;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.adapter.in.web.teacher.dto.response.TeacherInfo;

public interface LoadTeacherPort {

  Optional<TeacherInfo> loadByUserId(UUID userId);

  Optional<TeacherInfo> loadById(UUID id);
}
