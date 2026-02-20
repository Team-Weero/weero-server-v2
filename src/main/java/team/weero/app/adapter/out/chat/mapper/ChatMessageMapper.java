package team.weero.app.adapter.out.chat.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.chat.entity.ChatMessageJpaEntity;
import team.weero.app.adapter.out.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.domain.chat.ChatMessage;

@Component
@RequiredArgsConstructor
public class ChatMessageMapper {

  public ChatMessageJpaEntity toEntity(
      ChatMessage chatMessage, UserJpaEntity user, ChatRoomJpaEntity chatRoom) {
    return ChatMessageJpaEntity.builder()
        .text(chatMessage.getText())
        .readStatus(false)
        .user(user)
        .chatRoom(chatRoom)
        .build();
  }

  public ChatMessage toDomain(ChatMessageJpaEntity entity) {
    return ChatMessage.builder()
        .id(entity.getId())
        .chatRoomId(entity.getChatRoom().getId())
        .senderId(entity.getUser().getId())
        .text(entity.getText())
        .sendDate(entity.getCreatedAt())
        .readStatus(entity.getReadStatus())
        .build();
  }
}
