package team.weero.app.application.port.in;

import java.util.UUID;

public interface GetNoticeUseCase {

  NoticeResponse getById(UUID noticeId);
}
