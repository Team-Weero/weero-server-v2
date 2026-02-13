package team.weero.app.application.service.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.notice.GetNoticeListUseCase;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;
import team.weero.app.application.port.in.notice.dto.response.NoticeListInfo;
import team.weero.app.application.port.out.notice.LoadNoticePort;
import team.weero.app.domain.notice.Notice;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllNoticeService implements GetNoticeListUseCase {

  private final LoadNoticePort loadNoticePort;

  @Override
  public NoticeListInfo execute(int page, int size) {

    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Notice> noticePage = loadNoticePort.loadAll(pageRequest);

    return new NoticeListInfo(
        noticePage.getContent().stream()
            .map(
                notice ->
                    new NoticeInfo(
                        notice.getId(),
                        notice.getTitle(),
                        notice.getContent(),
                        notice.getWriterId(),
                        notice.getCreatedAt()))
            .toList(),
        noticePage.getTotalPages(),
        noticePage.getNumber());
  }
}
