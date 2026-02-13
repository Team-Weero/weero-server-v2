package team.weero.app.application.port.in.counsel;

import java.util.UUID;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;

public interface ApproveCounselRequestUseCase {
  CounselRequestInfo execute(UUID id, UUID teacherId);
}
