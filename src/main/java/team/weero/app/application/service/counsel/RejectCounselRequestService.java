package team.weero.app.application.service.counsel;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.teacher.dto.response.TeacherInfo;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.counsel.ForbiddenCounselRequestAccessException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.RejectCounselRequestUseCase;
import team.weero.app.application.port.out.counsel.CheckCounselRequestOwnerPort;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class RejectCounselRequestService implements RejectCounselRequestUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;
  private final SaveCounselRequestPort saveCounselRequestPort;
  private final CheckCounselRequestOwnerPort checkCounselRequestOwnerPort;
  private final LoadTeacherPort loadTeacherPort;

  @Override
  public void execute(UUID id, UUID userId) {
    TeacherInfo teacherInfo =
        loadTeacherPort.loadByUserId(userId).orElseThrow(TeacherNotFoundException::new);

    if (!checkCounselRequestOwnerPort.isTeacherOwner(id, teacherInfo.id())) {
      throw new ForbiddenCounselRequestAccessException();
    }

    CounselRequest existing =
        loadCounselRequestPort.loadById(id).orElseThrow(CounselRequestNotFoundException::new);

    CounselRequest updated = existing.reject();

    saveCounselRequestPort.save(updated);
  }
}
