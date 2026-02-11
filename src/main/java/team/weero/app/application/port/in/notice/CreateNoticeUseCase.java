package team.weero.app.application.port.in.notice;

import team.weero.app.application.port.in.notice.dto.request.CreateNoticeCommand;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;

public interface CreateNoticeUseCase {

  NoticeInfo execute(CreateNoticeCommand command);
}
