package team.weero.app.application.port.in.chat.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.domain.chat.ChatMessage;

public record ChatMessageInfo(
    UUID messageId, UUID senderId, String text, LocalDateTime sendDate, Boolean readStatus) {
  public static ChatMessageInfo from(ChatMessage chatMessage) {
    return new ChatMessageInfo(
        chatMessage.getId(),
        chatMessage.getSenderId(),
        chatMessage.getText(),
        chatMessage.getSendDate(),
        chatMessage.getReadStatus());
  }
}
