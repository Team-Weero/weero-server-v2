package team.weero.app.application.port.in.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;

public interface GetAllNoticesUseCase {
  Page<NoticeResponse> execute(Pageable pageable);
}
