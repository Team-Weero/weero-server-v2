package team.weero.app.application.port.in.notice;

import java.util.UUID;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;

public interface GetNoticeByIdUseCase {
  NoticeResponse execute(UUID id);
}
