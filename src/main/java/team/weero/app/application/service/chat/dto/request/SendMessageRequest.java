package team.weero.app.application.chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SendMessageRequest(
    @NotNull(message = "채팅방 ID는 필수입니다")
    UUID chatRoomId,

    @NotBlank(message = "메시지 내용은 필수입니다")
    @Size(max = 255, message = "메시지 내용은 255자 이하여야 합니다")
    String text
) {
}
