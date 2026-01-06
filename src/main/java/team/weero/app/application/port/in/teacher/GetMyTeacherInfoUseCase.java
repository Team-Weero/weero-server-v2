package team.weero.app.application.port.in.teacher;

import team.weero.app.application.service.teacher.dto.response.TeacherResponse;

public interface GetMyTeacherInfoUseCase {
  TeacherResponse execute(String accountId);
}
