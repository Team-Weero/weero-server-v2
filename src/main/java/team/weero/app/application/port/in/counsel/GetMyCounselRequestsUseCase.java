package team.weero.app.application.port.in.counsel;

import java.util.UUID;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestListInfo;

public interface GetMyCounselRequestsUseCase {
  CounselRequestListInfo execute(UUID studentId);
}
