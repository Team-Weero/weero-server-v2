package team.weero.app.application.port.out;

import java.util.UUID;

public interface DeleteNoticePort {

  void delete(UUID id);
}
