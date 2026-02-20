package team.weero.app.adapter.in.web.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;

@Schema(description = "채팅 메시지 응답")
@Builder
public record ChatMessageResponse(
    @Schema(description = "메시지 ID") UUID messageId,
    @Schema(description = "발신자 ID") UUID senderId,
    @Schema(description = "메시지 내용") String text,
    @Schema(description = "전송 시각") LocalDateTime sendDate) {

  public static ChatMessageResponse from(ChatMessageInfo info) {
    return ChatMessageResponse.builder()
        .messageId(info.messageId())
        .senderId(info.senderId())
        .text(info.text())
        .sendDate(info.sendDate())
        .build();
  }
}
