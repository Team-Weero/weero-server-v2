package team.weero.app.application.port.in.counsel;

import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;

import java.util.UUID;

public interface ApproveCounselRequestUseCase {
    CounselRequestResponse execute(UUID id, UUID teacherId);
}
