package team.weero.app.application.chat.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageResponse(
    UUID id,
    UUID chatRoomId,
    UUID senderId,
    String text,
    LocalDateTime sendDate,
    boolean readStatus
) {
}
