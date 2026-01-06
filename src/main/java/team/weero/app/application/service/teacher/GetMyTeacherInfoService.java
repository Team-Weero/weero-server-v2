package team.weero.app.application.service.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.teacher.GetMyTeacherInfoUseCase;
import team.weero.app.application.port.out.teacher.TeacherPort;
import team.weero.app.application.service.teacher.dto.response.TeacherResponse;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyTeacherInfoService implements GetMyTeacherInfoUseCase {

  private final TeacherPort teacherPort;

  public TeacherResponse execute(String accountId) {
    Teacher teacher =
        teacherPort
            .findByAccountId(accountId)
            .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

    return TeacherResponse.from(teacher);
  }
}
