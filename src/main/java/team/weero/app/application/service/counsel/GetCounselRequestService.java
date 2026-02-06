package team.weero.app.application.service.counsel;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.port.in.counsel.GetCounselRequestUseCase;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCounselRequestService implements GetCounselRequestUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;

  @Override
  public CounselRequestResponse execute(UUID id) {
    CounselRequest counselRequest =
        loadCounselRequestPort.loadById(id).orElseThrow(CounselRequestNotFoundException::new);

    return new CounselRequestResponse(
        counselRequest.getId(),
        counselRequest.getStatus(),
        counselRequest.getGender(),
        counselRequest.isHasCounselingExperience(),
        counselRequest.getCategory(),
        counselRequest.getStudentId(),
        counselRequest.getStudentName(),
        counselRequest.getTeacherId(),
        counselRequest.getTeacherName(),
        counselRequest.getCreatedAt(),
        counselRequest.getUpdatedAt());
  }
}
