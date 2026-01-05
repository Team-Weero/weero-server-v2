package team.weero.app.application.service.chat;
import team.weero.app.application.port.in.chat.GetChatRoomMessagesUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.chat.dto.response.MessageResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.chat.exception.ChatRoomNotFoundException;
import team.weero.app.domain.chat.exception.UnauthorizedChatAccessException;
import team.weero.app.domain.chat.model.ChatRoom;
import team.weero.app.application.port.out.chat.ChatRoomRepository;
import team.weero.app.application.port.out.chat.MessageRepository;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetChatRoomMessagesService implements GetChatRoomMessagesUseCase {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final StudentJpaRepository studentJpaRepository;

    public GetChatRoomMessagesService(MessageRepository messageRepository,
                                     ChatRoomRepository chatRoomRepository,
                                     StudentJpaRepository studentJpaRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public List<MessageResponse> execute(UUID chatRoomId, String accountId) {
        // Find user
        var user = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // Find chat room
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);

        // Verify user is participant
        if (!chatRoom.hasParticipant(user.getId())) {
            throw UnauthorizedChatAccessException.EXCEPTION;
        }

        // Get messages
        return messageRepository.findByChatRoomIdOrderBySendDateAsc(chatRoomId).stream()
                .map(message -> new MessageResponse(
                    message.getId(),
                    message.getChatRoomId(),
                    message.getSenderId(),
                    message.getText(),
                    message.getSendDate(),
                    message.isReadStatus()
                ))
                .toList();
    }
}
