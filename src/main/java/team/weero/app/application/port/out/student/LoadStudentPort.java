package team.weero.app.application.port.out.student;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;

public interface LoadStudentPort {

  Optional<StudentInfo> loadByUserId(UUID userId);
}
