package team.weero.app.application.port.out;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.application.port.in.StudentInfo;

public interface LoadStudentPort {

  Optional<StudentInfo> loadByUserId(UUID userId);
}
