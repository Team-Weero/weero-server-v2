package team.weero.app.application.port.in.counsel;

import java.util.UUID;
import team.weero.app.adapter.in.web.counsel.dto.request.UpdateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;

public interface UpdateCounselRequestUseCase {
  CounselRequestResponse execute(UUID id, UpdateCounselRequestRequest request, UUID studentId);
}
