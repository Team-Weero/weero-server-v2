package team.weero.app.application.port.in.counsel;

import java.util.UUID;
import team.weero.app.adapter.in.web.counsel.dto.request.CreateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;

public interface CreateCounselRequestUseCase {
  CounselRequestResponse execute(CreateCounselRequestRequest request, UUID studentId);
}
