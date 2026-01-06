package team.weero.app.application.service.chat;
import team.weero.app.application.port.in.chat.CreateChatRoomUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.chat.dto.request.CreateChatRoomRequest;
import team.weero.app.application.service.chat.dto.response.ChatRoomResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.chat.model.ChatRoom;
import team.weero.app.application.port.out.chat.ChatRoomRepository;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;

import java.util.UUID;

@Service
@Transactional
public class CreateChatRoomService implements CreateChatRoomUseCase {

    private final ChatRoomRepository chatRoomRepository;
    private final StudentJpaRepository studentJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public CreateChatRoomService(ChatRoomRepository chatRoomRepository,
                                StudentJpaRepository studentJpaRepository,
                                UserJpaRepository userJpaRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.studentJpaRepository = studentJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public ChatRoomResponse execute(CreateChatRoomRequest request, String accountId) {
        var startUserStudent = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        var receiverUser = userJpaRepository.findById(request.receiverUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        var existingChatRoom = chatRoomRepository.findByParticipants(startUserStudent.getId(), receiverUser.getId());
        if (existingChatRoom.isPresent()) {
            ChatRoom chatRoom = existingChatRoom.get();
            return new ChatRoomResponse(
                chatRoom.getId(),
                chatRoom.getStartUserId(),
                chatRoom.getReceiverUserId(),
                null,
                0
            );
        }

        ChatRoom chatRoom = ChatRoom.builder()
                .startUserId(startUserStudent.getId())
                .receiverUserId(receiverUser.getId())
                .build();

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        return new ChatRoomResponse(
            savedChatRoom.getId(),
            savedChatRoom.getStartUserId(),
            savedChatRoom.getReceiverUserId(),
            null,
            0
        );
    }
}
