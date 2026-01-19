package team.weero.app.application.port.in.notice;

import team.weero.app.adapter.in.web.notice.dto.response.NoticeResponse;

import java.util.UUID;

public interface GetNoticeUseCase {

  NoticeResponse getById(UUID noticeId);
}
