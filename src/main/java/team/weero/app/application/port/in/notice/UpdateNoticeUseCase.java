package team.weero.app.application.port.in.notice;

import team.weero.app.application.port.in.notice.dto.request.UpdateNoticeCommand;
import team.weero.app.adapter.in.web.notice.dto.response.NoticeResponse;

public interface UpdateNoticeUseCase {

  NoticeResponse update(UpdateNoticeCommand command);
}
