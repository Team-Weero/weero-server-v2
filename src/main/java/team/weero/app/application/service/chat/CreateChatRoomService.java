package team.weero.app.application.chat.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.chat.dto.request.CreateChatRoomRequest;
import team.weero.app.application.chat.dto.response.ChatRoomResponse;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.chat.model.ChatRoom;
import team.weero.app.domain.chat.repository.ChatRoomRepository;
import team.weero.app.infrastructure.persistence.student.repository.StudentJpaRepository;
import team.weero.app.infrastructure.persistence.user.repository.UserJpaRepository;

import java.util.UUID;

@Service
@Transactional
public class CreateChatRoomUseCase {

    private final ChatRoomRepository chatRoomRepository;
    private final StudentJpaRepository studentJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public CreateChatRoomUseCase(ChatRoomRepository chatRoomRepository,
                                StudentJpaRepository studentJpaRepository,
                                UserJpaRepository userJpaRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.studentJpaRepository = studentJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public ChatRoomResponse execute(CreateChatRoomRequest request, String accountId) {
        // Find start user (current user) - assuming they're a student
        var startUserStudent = studentJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // Verify receiver user exists
        var receiverUser = userJpaRepository.findById(request.receiverUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // Check if chat room already exists
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

        // Create new chat room
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
