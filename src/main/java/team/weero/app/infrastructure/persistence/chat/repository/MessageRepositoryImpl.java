package team.weero.app.infrastructure.persistence.chat.repository;

import org.springframework.stereotype.Repository;
import team.weero.app.domain.chat.model.Message;
import team.weero.app.domain.chat.repository.MessageRepository;
import team.weero.app.infrastructure.persistence.chat.entity.ChatRoomJpaEntity;
import team.weero.app.infrastructure.persistence.chat.entity.MessageJpaEntity;
import team.weero.app.infrastructure.persistence.chat.mapper.MessageMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageJpaRepository messageJpaRepository;
    private final MessageMapper messageMapper;
    private final ChatRoomJpaRepository chatRoomJpaRepository;

    public MessageRepositoryImpl(MessageJpaRepository messageJpaRepository,
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
        return messageJpaRepository.findById(id)
                .map(messageMapper::toDomain);
    }

    @Override
    public List<Message> findByChatRoomId(UUID chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomJpaRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        return messageJpaRepository.findByChatRoom(chatRoom).stream()
                .map(messageMapper::toDomain)
                .toList();
    }

    @Override
    public List<Message> findByChatRoomIdOrderBySendDateAsc(UUID chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomJpaRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        return messageJpaRepository.findByChatRoomOrderBySendDateAsc(chatRoom).stream()
                .map(messageMapper::toDomain)
                .toList();
    }

    @Override
    public List<Message> findUnreadMessagesByChatRoomId(UUID chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomJpaRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        return messageJpaRepository.findUnreadMessagesByChatRoom(chatRoom).stream()
                .map(messageMapper::toDomain)
                .toList();
    }

    @Override
    public int countUnreadMessagesByChatRoomIdAndReceiverId(UUID chatRoomId, UUID receiverId) {
        return messageJpaRepository.countUnreadMessagesByChatRoomIdAndReceiverId(chatRoomId, receiverId);
    }

    @Override
    public void deleteById(UUID id) {
        messageJpaRepository.deleteById(id);
    }
}
