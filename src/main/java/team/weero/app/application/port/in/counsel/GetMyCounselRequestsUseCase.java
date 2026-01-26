package team.weero.app.application.port.in.counsel;

import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;

import java.util.UUID;

public interface GetMyCounselRequestsUseCase {
    CounselRequestListResponse execute(UUID studentId);
}
