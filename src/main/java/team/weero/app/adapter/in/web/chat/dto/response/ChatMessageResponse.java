package team.weero.app.adapter.in.web.chat.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;

@Builder
public record ChatMessageResponse(
    UUID messageId, UUID senderId, String text, LocalDateTime sendDate, Boolean readStatus) {
  public static ChatMessageResponse from(ChatMessageInfo info) {
    return ChatMessageResponse.builder()
        .messageId(info.messageId())
        .senderId(info.senderId())
        .text(info.text())
        .sendDate(info.sendDate())
        .readStatus(info.readStatus())
        .build();
  }
}
