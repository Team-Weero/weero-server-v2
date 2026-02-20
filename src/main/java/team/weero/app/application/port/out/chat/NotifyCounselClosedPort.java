package team.weero.app.application.port.out.chat;

import java.io.IOException;
import java.util.UUID;

public interface NotifyCounselClosedPort {
  void notify(UUID chatRoomId) throws IOException;
}
