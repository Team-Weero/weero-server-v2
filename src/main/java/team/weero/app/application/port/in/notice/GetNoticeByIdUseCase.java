package team.weero.app.application.port.in.notice;

import java.util.UUID;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;
import team.weero.app.domain.notice.model.Notice;

public interface GetNoticeByIdUseCase {
    NoticeResponse execute(UUID id);
}
