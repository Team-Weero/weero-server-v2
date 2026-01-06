package team.weero.app.adapter.out.persistence.chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.persistence.chat.entity.MessageJpaEntity;
import team.weero.app.adapter.out.persistence.chat.mapper.MessageMapper;
import team.weero.app.adapter.out.persistence.chat.repository.ChatRoomJpaRepository;
import team.weero.app.adapter.out.persistence.chat.repository.MessageJpaRepository;
import team.weero.app.application.port.out.chat.MessagePort;
import team.weero.app.domain.chat.model.Message;

@Component
public class MessagePersistenceAdapter implements MessagePort {

  private final MessageJpaRepository messageJpaRepository;
  private final MessageMapper messageMapper;
  private final ChatRoomJpaRepository chatRoomJpaRepository;

  public MessagePersistenceAdapter(
      MessageJpaRepository messageJpaRepository,
      MessageMapper messageMapper,
      ChatRoomJpaRepository chatRoomJpaRepository) {
    this.messageJpaRepository = messageJpaRepository;
    this.messageMapper = messageMapper;
    this.chatRoomJpaRepository = chatRoomJpaRepository;
  }

  @Override
  public Message save(Message message) {
    MessageJpaEntity entity = messageMapper.toEntity(message);
    MessageJpaEntity savedEntity = messageJpaRepository.save(entity);
    return messageMapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Message> findById(UUID id) {
    return messageJpaRepository.findById(id).map(messageMapper::toDomain);
  }

  @Override
  public List<Message> findByChatRoomId(UUID chatRoomId) {
    ChatRoomJpaEntity chatRoom =
        chatRoomJpaRepository
            .findById(chatRoomId)
            .orElseThrow(() -> new RuntimeException("Chat room not found"));
    return messageJpaRepository.findByChatRoom(chatRoom).stream()
        .map(messageMapper::toDomain)
        .toList();
  }

  @Override
  public List<Message> findByChatRoomIdOrderBySendDateAsc(UUID chatRoomId) {
    ChatRoomJpaEntity chatRoom =
        chatRoomJpaRepository
            .findById(chatRoomId)
            .orElseThrow(() -> new RuntimeException("Chat room not found"));
    return messageJpaRepository.findByChatRoomOrderBySendDateAsc(chatRoom).stream()
        .map(messageMapper::toDomain)
        .toList();
  }

  @Override
  public List<Message> findUnreadMessagesByChatRoomId(UUID chatRoomId) {
    ChatRoomJpaEntity chatRoom =
        chatRoomJpaRepository
            .findById(chatRoomId)
            .orElseThrow(() -> new RuntimeException("Chat room not found"));
    return messageJpaRepository.findUnreadMessagesByChatRoom(chatRoom).stream()
        .map(messageMapper::toDomain)
        .toList();
  }

  @Override
  public int countUnreadMessagesByChatRoomIdAndReceiverId(UUID chatRoomId, UUID receiverId) {
    return messageJpaRepository.countUnreadMessagesByChatRoomIdAndReceiverId(
        chatRoomId, receiverId);
  }

  @Override
  public void deleteById(UUID id) {
    messageJpaRepository.deleteById(id);
  }
}
