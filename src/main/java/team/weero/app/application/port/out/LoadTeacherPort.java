package team.weero.app.application.port.out;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.application.port.in.TeacherInfo;

public interface LoadTeacherPort {

  Optional<TeacherInfo> loadByUserId(UUID userId);
}
