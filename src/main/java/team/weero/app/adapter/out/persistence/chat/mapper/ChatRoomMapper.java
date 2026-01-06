package team.weero.app.adapter.out.persistence.chat.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;
import team.weero.app.domain.chat.model.ChatRoom;

@Component
public class ChatRoomMapper {

  private final UserJpaRepository userJpaRepository;

  public ChatRoomMapper(UserJpaRepository userJpaRepository) {
    this.userJpaRepository = userJpaRepository;
  }

  public ChatRoom toDomain(ChatRoomJpaEntity entity) {
    return ChatRoom.builder()
        .id(entity.getId())
        .startUserId(entity.getStartUser().getId())
        .receiverUserId(entity.getReceiverUser().getId())
        .build();
  }

  public ChatRoomJpaEntity toEntity(ChatRoom domain) {
    UserJpaEntity startUser =
        userJpaRepository
            .findById(domain.getStartUserId())
            .orElseThrow(() -> new RuntimeException("Start user not found"));
    UserJpaEntity receiverUser =
        userJpaRepository
            .findById(domain.getReceiverUserId())
            .orElseThrow(() -> new RuntimeException("Receiver user not found"));

    return ChatRoomJpaEntity.builder()
        .id(domain.getId())
        .startUser(startUser)
        .receiverUser(receiverUser)
        .build();
  }
}
