package team.weero.app.application.port.in;

import java.util.UUID;

public interface DeleteNoticeUseCase {

  void delete(UUID noticeId);
}
