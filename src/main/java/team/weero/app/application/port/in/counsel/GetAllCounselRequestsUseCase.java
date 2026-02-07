package team.weero.app.application.port.in.counsel;

import java.util.UUID;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;

public interface GetAllCounselRequestsUseCase {
  CounselRequestListResponse execute();

  CounselRequestListResponse execute(UUID teacherId);
}
