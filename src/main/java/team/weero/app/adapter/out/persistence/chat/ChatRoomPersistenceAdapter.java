package team.weero.app.adapter.out.persistence.chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.persistence.chat.mapper.ChatRoomMapper;
import team.weero.app.adapter.out.persistence.chat.repository.ChatRoomJpaRepository;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;
import team.weero.app.application.port.out.chat.ChatRoomPort;
import team.weero.app.domain.chat.model.ChatRoom;

@Component
public class ChatRoomPersistenceAdapter implements ChatRoomPort {

  private final ChatRoomJpaRepository chatRoomJpaRepository;
  private final ChatRoomMapper chatRoomMapper;
  private final UserJpaRepository userJpaRepository;

  public ChatRoomPersistenceAdapter(
      ChatRoomJpaRepository chatRoomJpaRepository,
      ChatRoomMapper chatRoomMapper,
      UserJpaRepository userJpaRepository) {
    this.chatRoomJpaRepository = chatRoomJpaRepository;
    this.chatRoomMapper = chatRoomMapper;
    this.userJpaRepository = userJpaRepository;
  }

  @Override
  public ChatRoom save(ChatRoom chatRoom) {
    ChatRoomJpaEntity entity = chatRoomMapper.toEntity(chatRoom);
    ChatRoomJpaEntity savedEntity = chatRoomJpaRepository.save(entity);
    return chatRoomMapper.toDomain(savedEntity);
  }

  @Override
  public Optional<ChatRoom> findById(UUID id) {
    return chatRoomJpaRepository.findById(id).map(chatRoomMapper::toDomain);
  }

  @Override
  public List<ChatRoom> findByUserId(UUID userId) {
    return chatRoomJpaRepository.findByUserId(userId).stream()
        .map(chatRoomMapper::toDomain)
        .toList();
  }

  @Override
  public Optional<ChatRoom> findByParticipants(UUID startUserId, UUID receiverUserId) {
    UserJpaEntity user1 =
        userJpaRepository
            .findById(startUserId)
            .orElseThrow(() -> new RuntimeException("Start user not found"));
    UserJpaEntity user2 =
        userJpaRepository
            .findById(receiverUserId)
            .orElseThrow(() -> new RuntimeException("Receiver user not found"));

    return chatRoomJpaRepository.findByParticipants(user1, user2).map(chatRoomMapper::toDomain);
  }

  @Override
  public void deleteById(UUID id) {
    chatRoomJpaRepository.deleteById(id);
  }
}
