package team.weero.app.application.port.out.chat;

import team.weero.app.domain.chat.model.ChatRoom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomPort {
    ChatRoom save(ChatRoom chatRoom);
    Optional<ChatRoom> findById(UUID id);
    List<ChatRoom> findByUserId(UUID userId);
    Optional<ChatRoom> findByParticipants(UUID startUserId, UUID receiverUserId);
    void deleteById(UUID id);
}
