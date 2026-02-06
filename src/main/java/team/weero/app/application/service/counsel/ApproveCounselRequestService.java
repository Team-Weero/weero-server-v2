package team.weero.app.application.service.counsel;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.adapter.in.web.teacher.dto.response.TeacherInfo;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.counsel.ForbiddenCounselRequestAccessException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.ApproveCounselRequestUseCase;
import team.weero.app.application.port.out.counsel.CheckCounselRequestOwnerPort;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class ApproveCounselRequestService implements ApproveCounselRequestUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;
  private final SaveCounselRequestPort saveCounselRequestPort;
  private final CheckCounselRequestOwnerPort checkCounselRequestOwnerPort;
  private final LoadTeacherPort loadTeacherPort;

  @Override
  public CounselRequestResponse execute(UUID id, UUID userId) {
    TeacherInfo teacherInfo =
        loadTeacherPort.loadByUserId(userId).orElseThrow(TeacherNotFoundException::new);

    if (!checkCounselRequestOwnerPort.isTeacherOwner(id, teacherInfo.id())) {
      throw new ForbiddenCounselRequestAccessException();
    }

    CounselRequest existing =
        loadCounselRequestPort.loadById(id).orElseThrow(CounselRequestNotFoundException::new);

    CounselRequest updated = existing.approve();

    CounselRequest saved = saveCounselRequestPort.save(updated);

    return new CounselRequestResponse(
        saved.getId(),
        saved.getStatus(),
        saved.getGender(),
        saved.isHasCounselingExperience(),
        saved.getCategory(),
        saved.getStudentId(),
        saved.getStudentName(),
        saved.getTeacherId(),
        saved.getTeacherName(),
        saved.getCreatedAt(),
        saved.getUpdatedAt());
  }
}
