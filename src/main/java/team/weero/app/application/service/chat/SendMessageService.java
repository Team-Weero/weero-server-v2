package team.weero.app.application.service.chat;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.application.port.in.chat.SendMessageUseCase;
import team.weero.app.application.port.out.chat.ChatRoomPort;
import team.weero.app.application.port.out.chat.MessagePort;
import team.weero.app.application.service.chat.dto.request.SendMessageRequest;
import team.weero.app.application.service.chat.dto.response.MessageResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.chat.exception.ChatRoomNotFoundException;
import team.weero.app.domain.chat.exception.UnauthorizedChatAccessException;
import team.weero.app.domain.chat.model.ChatRoom;
import team.weero.app.domain.chat.model.Message;

@Service
@Transactional
public class SendMessageService implements SendMessageUseCase {

  private final MessagePort messagePort;
  private final ChatRoomPort chatRoomPort;
  private final StudentJpaRepository studentJpaRepository;

  public SendMessageService(
      MessagePort messagePort,
      ChatRoomPort chatRoomPort,
      StudentJpaRepository studentJpaRepository) {
    this.messagePort = messagePort;
    this.chatRoomPort = chatRoomPort;
    this.studentJpaRepository = studentJpaRepository;
  }

  public MessageResponse execute(SendMessageRequest request, String accountId) {
    var sender =
        studentJpaRepository
            .findByAccountId(accountId)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

    ChatRoom chatRoom =
        chatRoomPort
            .findById(request.chatRoomId())
            .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);

    if (!chatRoom.hasParticipant(sender.getId())) {
      throw UnauthorizedChatAccessException.EXCEPTION;
    }

    Message message =
        Message.builder()
            .chatRoomId(chatRoom.getId())
            .senderId(sender.getId())
            .text(request.text())
            .sendDate(LocalDateTime.now())
            .readStatus(false)
            .build();

    Message savedMessage = messagePort.save(message);

    return new MessageResponse(
        savedMessage.getId(),
        savedMessage.getChatRoomId(),
        savedMessage.getSenderId(),
        savedMessage.getText(),
        savedMessage.getSendDate(),
        savedMessage.isReadStatus());
  }
}
