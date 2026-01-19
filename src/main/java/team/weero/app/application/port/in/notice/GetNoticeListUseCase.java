package team.weero.app.application.port.in.notice;

import team.weero.app.adapter.in.web.notice.dto.response.NoticeListResponse;

public interface GetNoticeListUseCase {

  NoticeListResponse getList(int page, int size);
}
