package team.weero.app.domain.chat.repository;

import team.weero.app.domain.chat.model.ChatRoom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomRepository {
    ChatRoom save(ChatRoom chatRoom);
    Optional<ChatRoom> findById(UUID id);
    List<ChatRoom> findByUserId(UUID userId);
    Optional<ChatRoom> findByParticipants(UUID startUserId, UUID receiverUserId);
    void deleteById(UUID id);
}
