package team.weero.app.application.port.out.chat;

import java.util.UUID;

public interface CheckChatParticipantPort {
  boolean isParticipant(UUID chatRoomId, UUID userId);
}
