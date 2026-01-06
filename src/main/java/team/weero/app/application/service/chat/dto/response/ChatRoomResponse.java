package team.weero.app.application.service.chat.dto.response;

import java.util.UUID;

public record ChatRoomResponse(
    UUID id, UUID startUserId, UUID receiverUserId, String lastMessage, int unreadCount) {}
