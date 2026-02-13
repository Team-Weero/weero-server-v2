package team.weero.app.application.service.counsel;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.counsel.GetMyCounselRequestsUseCase;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestListInfo;
import team.weero.app.application.port.in.student.dto.response.StudentInfo;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyCounselRequestsService implements GetMyCounselRequestsUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public CounselRequestListInfo execute(UUID userId) {
    StudentInfo studentInfo =
        loadStudentPort.loadByUserId(userId).orElseThrow(StudentNotFoundException::new);

    List<CounselRequest> counselRequests =
        loadCounselRequestPort.loadAllByStudentId(studentInfo.id());

    List<CounselRequestInfo> responses =
        counselRequests.stream()
            .map(request -> CounselRequestInfo.from(request, studentInfo))
            .toList();

    return new CounselRequestListInfo(responses);
  }
}
