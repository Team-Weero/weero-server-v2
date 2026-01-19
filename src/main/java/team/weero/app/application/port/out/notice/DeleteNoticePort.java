package team.weero.app.application.port.out.notice;

import java.util.UUID;

public interface DeleteNoticePort {

  void delete(UUID id);
}
