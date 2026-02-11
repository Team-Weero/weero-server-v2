package team.weero.app.application.port.in.notice;

import team.weero.app.application.port.in.notice.dto.request.UpdateNoticeCommand;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;

public interface UpdateNoticeUseCase {

  NoticeInfo execute(UpdateNoticeCommand command);
}
