package team.weero.app.application.port.out;

import java.util.UUID;

public interface CheckNoticeOwnerPort {

  boolean isOwner(UUID noticeId, UUID userId);
}
