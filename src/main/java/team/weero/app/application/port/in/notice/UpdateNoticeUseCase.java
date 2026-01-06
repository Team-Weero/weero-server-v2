package team.weero.app.application.port.in.notice;

import java.util.UUID;
import team.weero.app.application.service.notice.dto.request.UpdateNoticeRequest;

public interface UpdateNoticeUseCase {
  void execute(UUID id, UpdateNoticeRequest request, String accountId);
}
