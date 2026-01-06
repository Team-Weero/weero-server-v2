package team.weero.app.application.port.in.notice;

import java.util.List;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;

public interface GetMyNoticesUseCase {
  List<NoticeResponse> execute(String accountId);
}
