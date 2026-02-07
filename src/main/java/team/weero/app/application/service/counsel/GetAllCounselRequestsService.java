package team.weero.app.application.service.counsel;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.application.port.in.counsel.GetAllCounselRequestsUseCase;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllCounselRequestsService implements GetAllCounselRequestsUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;

  @Override
  public CounselRequestListResponse execute() {
    List<CounselRequest> counselRequests = loadCounselRequestPort.loadAll();

    List<CounselRequestResponse> responses =
        counselRequests.stream().map(CounselRequestResponse::from).toList();

    return new CounselRequestListResponse(responses);
  }

  @Override
  public CounselRequestListResponse execute(UUID teacherId) {
    List<CounselRequest> counselRequests = loadCounselRequestPort.loadAllByTeacherId(teacherId);

    List<CounselRequestResponse> responses =
        counselRequests.stream().map(CounselRequestResponse::from).toList();

    return new CounselRequestListResponse(responses);
  }
}
