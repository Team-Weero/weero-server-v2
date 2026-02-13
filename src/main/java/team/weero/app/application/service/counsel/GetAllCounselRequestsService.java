package team.weero.app.application.service.counsel;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.GetAllCounselRequestsUseCase;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestListInfo;
import team.weero.app.application.port.in.student.dto.response.StudentInfo;
import team.weero.app.application.port.in.teacher.dto.response.TeacherInfo;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllCounselRequestsService implements GetAllCounselRequestsUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;
  private final LoadStudentPort loadStudentPort;
  private final LoadTeacherPort loadTeacherPort;

  @Override
  public CounselRequestListInfo execute() {
    List<CounselRequest> counselRequests = loadCounselRequestPort.loadAll();

    List<CounselRequestInfo> responses =
        counselRequests.stream()
            .map(
                request -> {
                  StudentInfo student =
                      loadStudentPort
                          .loadById(request.getStudentId())
                          .orElseThrow(StudentNotFoundException::new);
                  TeacherInfo teacher =
                      loadTeacherPort
                          .loadById(request.getTeacherId())
                          .orElseThrow(TeacherNotFoundException::new);
                  return CounselRequestInfo.from(request, student, teacher);
                })
            .toList();

    return new CounselRequestListInfo(responses);
  }

  @Override
  public CounselRequestListInfo execute(UUID teacherId) {
    List<CounselRequest> counselRequests = loadCounselRequestPort.loadAllByTeacherId(teacherId);
    List<CounselRequestInfo> responses =
        counselRequests.stream()
            .map(
                request -> {
                  StudentInfo student =
                      loadStudentPort
                          .loadById(request.getStudentId())
                          .orElseThrow(StudentNotFoundException::new);

                  TeacherInfo teacher =
                      loadTeacherPort
                          .loadById(request.getTeacherId())
                          .orElseThrow(TeacherNotFoundException::new);

                  return CounselRequestInfo.from(request, student, teacher);
                })
            .toList();

    return new CounselRequestListInfo(responses);
  }
}
