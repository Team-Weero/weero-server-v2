package team.weero.app.application.port.in.notice;

import team.weero.app.application.service.notice.dto.request.CreateNoticeRequest;
import team.weero.app.domain.notice.model.Notice;

public interface CreateNoticeUseCase {
    void execute(CreateNoticeRequest request, String accountId);
}
