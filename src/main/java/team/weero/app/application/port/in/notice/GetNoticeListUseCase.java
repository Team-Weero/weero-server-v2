package team.weero.app.application.port.in.notice;

import team.weero.app.application.port.in.notice.dto.response.NoticeListInfo;

public interface GetNoticeListUseCase {

  NoticeListInfo execute(int page, int size);
}
