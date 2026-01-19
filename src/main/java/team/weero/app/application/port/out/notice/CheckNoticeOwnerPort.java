package team.weero.app.application.port.out.notice;

import java.util.UUID;

public interface CheckNoticeOwnerPort {

  boolean isOwner(UUID noticeId, UUID userId);
}
