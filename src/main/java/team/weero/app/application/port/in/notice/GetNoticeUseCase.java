package team.weero.app.application.port.in.notice;

import java.util.UUID;
import team.weero.app.adapter.in.web.notice.dto.response.NoticeResponse;

public interface GetNoticeUseCase {

  NoticeResponse getById(UUID noticeId);
}
