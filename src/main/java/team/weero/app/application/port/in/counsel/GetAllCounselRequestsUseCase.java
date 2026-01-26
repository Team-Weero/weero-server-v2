package team.weero.app.application.port.in.counsel;

import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;

public interface GetAllCounselRequestsUseCase {
    CounselRequestListResponse execute();
}
