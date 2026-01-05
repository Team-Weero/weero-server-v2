package team.weero.app.adapter.out.persistence.chat.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.chat.model.Message;
import team.weero.app.adapter.out.persistence.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.persistence.chat.entity.MessageJpaEntity;
import team.weero.app.adapter.out.persistence.chat.repository.ChatRoomJpaRepository;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;

@Component
public class MessageMapper {

    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public MessageMapper(ChatRoomJpaRepository chatRoomJpaRepository, UserJpaRepository userJpaRepository) {
        this.chatRoomJpaRepository = chatRoomJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public Message toDomain(MessageJpaEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .chatRoomId(entity.getChatRoom().getId())
                .senderId(entity.getSender().getId())
                .text(entity.getText())
                .sendDate(entity.getSendDate())
                .readStatus(entity.isReadStatus())
                .build();
    }

    public MessageJpaEntity toEntity(Message domain) {
        ChatRoomJpaEntity chatRoom = chatRoomJpaRepository.findById(domain.getChatRoomId())
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        UserJpaEntity sender = userJpaRepository.findById(domain.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        return MessageJpaEntity.builder()
                .id(domain.getId())
                .chatRoom(chatRoom)
                .sender(sender)
                .text(domain.getText())
                .sendDate(domain.getSendDate())
                .readStatus(domain.isReadStatus())
                .build();
    }
}
