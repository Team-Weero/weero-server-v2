package team.weero.app.application.service.chat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;
import team.weero.app.application.port.in.chat.CreateChatRoomUseCase;
import team.weero.app.application.port.out.chat.ChatRoomPort;
import team.weero.app.application.service.chat.dto.request.CreateChatRoomRequest;
import team.weero.app.application.service.chat.dto.response.ChatRoomResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.chat.model.ChatRoom;

@Service
@Transactional
public class CreateChatRoomService implements CreateChatRoomUseCase {

  private final ChatRoomPort chatRoomPort;
  private final StudentJpaRepository studentJpaRepository;
  private final UserJpaRepository userJpaRepository;

  public CreateChatRoomService(
      ChatRoomPort chatRoomPort,
      StudentJpaRepository studentJpaRepository,
      UserJpaRepository userJpaRepository) {
    this.chatRoomPort = chatRoomPort;
    this.studentJpaRepository = studentJpaRepository;
    this.userJpaRepository = userJpaRepository;
  }

  public ChatRoomResponse execute(CreateChatRoomRequest request, String accountId) {
    var startUserStudent =
        studentJpaRepository
            .findByAccountId(accountId)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

    var receiverUser =
        userJpaRepository
            .findById(request.receiverUserId())
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

    var existingChatRoom =
        chatRoomPort.findByParticipants(startUserStudent.getId(), receiverUser.getId());
    if (existingChatRoom.isPresent()) {
      ChatRoom chatRoom = existingChatRoom.get();
      return new ChatRoomResponse(
          chatRoom.getId(), chatRoom.getStartUserId(), chatRoom.getReceiverUserId(), null, 0);
    }

    ChatRoom chatRoom =
        ChatRoom.builder()
            .startUserId(startUserStudent.getId())
            .receiverUserId(receiverUser.getId())
            .build();

    ChatRoom savedChatRoom = chatRoomPort.save(chatRoom);

    return new ChatRoomResponse(
        savedChatRoom.getId(),
        savedChatRoom.getStartUserId(),
        savedChatRoom.getReceiverUserId(),
        null,
        0);
  }
}
