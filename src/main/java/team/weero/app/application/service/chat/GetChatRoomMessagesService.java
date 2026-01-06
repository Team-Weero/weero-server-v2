package team.weero.app.application.service.chat;
import team.weero.app.application.port.in.chat.GetChatRoomMessagesUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.chat.dto.response.MessageResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.chat.exception.ChatRoomNotFoundException;
import team.weero.app.domain.chat.exception.UnauthorizedChatAccessException;
import team.weero.app.domain.chat.model.ChatRoom;
import team.weero.app.application.port.out.chat.ChatRoomPort;
import team.weero.app.application.port.out.chat.MessagePort;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetChatRoomMessagesService implements GetChatRoomMessagesUseCase {

    private final MessagePort messagePort;
    private final ChatRoomPort chatRoomPort;
    private final StudentJpaRepository studentJpaRepository;

    public GetChatRoomMessagesService(MessagePort messagePort,
                                      ChatRoomPort chatRoomPort,
                                      StudentJpaRepository studentJpaRepository) {
        this.messagePort = messagePort;
        this.chatRoomPort = chatRoomPort;
        this.studentJpaRepository = studentJpaRepository;
    }

    public List<MessageResponse> execute(UUID chatRoomId, String accountId) {
        var user = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ChatRoom chatRoom = chatRoomPort.findById(chatRoomId)
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);

        if (!chatRoom.hasParticipant(user.getId())) {
            throw UnauthorizedChatAccessException.EXCEPTION;
        }

        return messagePort.findByChatRoomIdOrderBySendDateAsc(chatRoomId).stream()
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
