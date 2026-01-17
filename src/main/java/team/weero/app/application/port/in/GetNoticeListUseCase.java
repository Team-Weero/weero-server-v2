package team.weero.app.application.port.in;

public interface GetNoticeListUseCase {

  NoticeListResponse getList(int page, int size);
}
