package team.weero.app.application.service.chat;
import team.weero.app.application.port.in.chat.SendMessageUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.chat.dto.request.SendMessageRequest;
import team.weero.app.application.service.chat.dto.response.MessageResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.chat.exception.ChatRoomNotFoundException;
import team.weero.app.domain.chat.exception.UnauthorizedChatAccessException;
import team.weero.app.domain.chat.model.ChatRoom;
import team.weero.app.domain.chat.model.Message;
import team.weero.app.application.port.out.chat.ChatRoomRepository;
import team.weero.app.application.port.out.chat.MessageRepository;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class SendMessageService implements SendMessageUseCase {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final StudentJpaRepository studentJpaRepository;

    public SendMessageService(MessageRepository messageRepository,
                             ChatRoomRepository chatRoomRepository,
                             StudentJpaRepository studentJpaRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public MessageResponse execute(SendMessageRequest request, String accountId) {
        // Find sender
        var sender = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // Find chat room
        ChatRoom chatRoom = chatRoomRepository.findById(request.chatRoomId())
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);

        // Verify sender is participant
        if (!chatRoom.hasParticipant(sender.getId())) {
            throw UnauthorizedChatAccessException.EXCEPTION;
        }

        // Create message
        Message message = Message.builder()
                .chatRoomId(chatRoom.getId())
                .senderId(sender.getId())
                .text(request.text())
                .sendDate(LocalDateTime.now())
                .readStatus(false)
                .build();

        Message savedMessage = messageRepository.save(message);

        return new MessageResponse(
            savedMessage.getId(),
            savedMessage.getChatRoomId(),
            savedMessage.getSenderId(),
            savedMessage.getText(),
            savedMessage.getSendDate(),
            savedMessage.isReadStatus()
        );
    }
}
