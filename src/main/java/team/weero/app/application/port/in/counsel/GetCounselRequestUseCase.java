package team.weero.app.application.port.in.counsel;

import java.util.UUID;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;

public interface GetCounselRequestUseCase {
  CounselRequestInfo execute(UUID id);

  CounselRequestInfo execute(UUID id, UUID teacherId);
}
