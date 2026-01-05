package team.weero.app.application.port.out.chat;

import team.weero.app.domain.chat.model.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository {
    Message save(Message message);
    Optional<Message> findById(UUID id);
    List<Message> findByChatRoomId(UUID chatRoomId);
    List<Message> findByChatRoomIdOrderBySendDateAsc(UUID chatRoomId);
    List<Message> findUnreadMessagesByChatRoomId(UUID chatRoomId);
    int countUnreadMessagesByChatRoomIdAndReceiverId(UUID chatRoomId, UUID receiverId);
    void deleteById(UUID id);
}
