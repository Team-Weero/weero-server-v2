package team.weero.app.application.port.out.chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.chat.model.Message;

public interface MessagePort {
  Message save(Message message);

  Optional<Message> findById(UUID id);

  List<Message> findByChatRoomId(UUID chatRoomId);

  List<Message> findByChatRoomIdOrderBySendDateAsc(UUID chatRoomId);

  List<Message> findUnreadMessagesByChatRoomId(UUID chatRoomId);

  int countUnreadMessagesByChatRoomIdAndReceiverId(UUID chatRoomId, UUID receiverId);

  void deleteById(UUID id);
}
