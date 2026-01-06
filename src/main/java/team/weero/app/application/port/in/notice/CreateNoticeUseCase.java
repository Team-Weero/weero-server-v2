package team.weero.app.application.port.in.notice;

import team.weero.app.application.service.notice.dto.request.CreateNoticeRequest;

public interface CreateNoticeUseCase {
  void execute(CreateNoticeRequest request, String accountId);
}
