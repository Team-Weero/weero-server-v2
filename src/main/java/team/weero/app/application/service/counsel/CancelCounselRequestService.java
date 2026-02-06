package team.weero.app.application.service.counsel;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.counsel.ForbiddenCounselRequestAccessException;
import team.weero.app.application.exception.counsel.InvalidCounselRequestStatusException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.counsel.CancelCounselRequestUseCase;
import team.weero.app.application.port.out.counsel.CheckCounselRequestOwnerPort;
import team.weero.app.application.port.out.counsel.DeleteCounselRequestPort;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class CancelCounselRequestService implements CancelCounselRequestUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;
  private final DeleteCounselRequestPort deleteCounselRequestPort;
  private final CheckCounselRequestOwnerPort checkCounselRequestOwnerPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public void execute(UUID id, UUID userId) {
    StudentInfo studentInfo =
        loadStudentPort.loadByUserId(userId).orElseThrow(StudentNotFoundException::new);

    // 소유권 검증
    if (!checkCounselRequestOwnerPort.isStudentOwner(id, studentInfo.id())) {
      throw new ForbiddenCounselRequestAccessException();
    }

    CounselRequest existing =
        loadCounselRequestPort.loadById(id).orElseThrow(CounselRequestNotFoundException::new);

    if (!existing.isPending()) {
      throw new InvalidCounselRequestStatusException();
    }

    deleteCounselRequestPort.softDelete(id);
  }
}
