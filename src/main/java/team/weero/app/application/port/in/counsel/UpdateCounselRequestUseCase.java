package team.weero.app.application.port.in.counsel;

import team.weero.app.adapter.in.web.counsel.dto.request.UpdateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;

import java.util.UUID;

public interface UpdateCounselRequestUseCase {
    CounselRequestResponse execute(UUID id, UpdateCounselRequestRequest request, UUID studentId);
}
