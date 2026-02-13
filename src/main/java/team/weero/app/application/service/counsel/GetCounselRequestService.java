package team.weero.app.application.service.counsel;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.counsel.ForbiddenCounselRequestAccessException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.GetCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;
import team.weero.app.application.port.in.student.dto.response.StudentInfo;
import team.weero.app.application.port.in.teacher.dto.response.TeacherInfo;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCounselRequestService implements GetCounselRequestUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;
  private final LoadStudentPort loadStudentPort;
  private final LoadTeacherPort loadTeacherPort;

  @Override
  public CounselRequestInfo execute(UUID id) {
    CounselRequest counselRequest =
        loadCounselRequestPort.loadById(id).orElseThrow(CounselRequestNotFoundException::new);

    StudentInfo student =
        loadStudentPort
            .loadById(counselRequest.getStudentId())
            .orElseThrow(StudentNotFoundException::new);
    TeacherInfo teacher =
        loadTeacherPort
            .loadById(counselRequest.getTeacherId())
            .orElseThrow(TeacherNotFoundException::new);

    return CounselRequestInfo.from(counselRequest, student, teacher);
  }

  @Override
  public CounselRequestInfo execute(UUID id, UUID teacherId) {
    CounselRequest counselRequest =
        loadCounselRequestPort.loadById(id).orElseThrow(CounselRequestNotFoundException::new);

    if (!counselRequest.getTeacherId().equals(teacherId)) {
      throw new ForbiddenCounselRequestAccessException();
    }

    StudentInfo student =
        loadStudentPort
            .loadById(counselRequest.getStudentId())
            .orElseThrow(StudentNotFoundException::new);
    TeacherInfo teacher =
        loadTeacherPort
            .loadById(counselRequest.getTeacherId())
            .orElseThrow(TeacherNotFoundException::new);

    return CounselRequestInfo.from(counselRequest, student, teacher);
  }
}
