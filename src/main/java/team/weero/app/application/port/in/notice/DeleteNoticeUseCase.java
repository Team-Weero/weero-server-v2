package team.weero.app.application.port.in.notice;

import java.util.UUID;

public interface DeleteNoticeUseCase {
  void execute(UUID id, String accountId);
}
