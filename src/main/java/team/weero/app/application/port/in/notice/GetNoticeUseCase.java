package team.weero.app.application.port.in.notice;

import java.util.UUID;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;

public interface GetNoticeUseCase {

  NoticeInfo execute(UUID noticeId);
}
