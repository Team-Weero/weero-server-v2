package team.weero.app.application.service.chat.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateChatRoomRequest(
    @NotNull(message = "수신자 ID는 필수입니다")
    UUID receiverUserId
) {
}
