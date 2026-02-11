package team.weero.app.application.service.notice;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.notice.NoticeNotFoundException;
import team.weero.app.application.port.in.notice.GetNoticeUseCase;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;
import team.weero.app.application.port.out.notice.LoadNoticePort;
import team.weero.app.domain.notice.Notice;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetNoticeService implements GetNoticeUseCase {

  private final LoadNoticePort loadNoticePort;

  @Override
  public NoticeInfo execute(UUID noticeId) {
    Notice notice =
        loadNoticePort.loadById(noticeId).orElseThrow(() -> NoticeNotFoundException.INSTANCE);

    return new NoticeInfo(
        notice.getId(),
        notice.getTitle(),
        notice.getContent(),
        notice.getWriterId(),
        notice.getCreatedAt());
  }
}
