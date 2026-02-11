package team.weero.app.application.port.in.notice;

import team.weero.app.application.port.in.notice.dto.request.CreateNoticeCommand;
import team.weero.app.adapter.in.web.notice.dto.response.NoticeResponse;

public interface CreateNoticeUseCase {

  NoticeResponse create(CreateNoticeCommand command);
}
