package team.weero.app.application.port.in.notice;

import team.weero.app.adapter.in.web.notice.dto.response.NoticeResponse;
import team.weero.app.application.port.in.notice.dto.request.CreateNoticeCommand;

public interface CreateNoticeUseCase {

  NoticeResponse create(CreateNoticeCommand command);
}
