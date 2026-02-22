package team.weero.app.adapter.out.chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.chat.entity.ChatMessageJpaEntity;
import team.weero.app.adapter.out.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.chat.mapper.ChatMessageMapper;
import team.weero.app.adapter.out.chat.mapper.ChatRoomMapper;
import team.weero.app.adapter.out.chat.repository.ChatMessageRepository;
import team.weero.app.adapter.out.chat.repository.ChatRoomRepository;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.adapter.out.counsel.repository.CounselRequestRepository;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.user.repository.UserRepository;
import team.weero.app.application.exception.chat.ChatRoomNotFoundException;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.exception.user.UserNotFoundException;
import team.weero.app.application.port.out.chat.*;
import team.weero.app.domain.chat.ChatMessage;
import team.weero.app.domain.chat.ChatRoom;

@Component
@RequiredArgsConstructor
public class ChatPersistenceAdapter
        implements SaveChatRoomPort,
        SaveMessagePort,
        LoadChatRoomPort,
        LoadChatMessagePort,
        CheckChatParticipantPort {

  private final ChatRoomRepository chatRoomRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;
  private final UserRepository userRepository;
  private final CounselRequestRepository counselRequestRepository;
  private final ChatMessageRepository chatMessageRepository;
  private final ChatRoomMapper chatRoomMapper;
  private final ChatMessageMapper chatMessageMapper;

  @Override
  @Transactional
  public ChatRoom save(ChatRoom chatRoom) {
    StudentJpaEntity student =
            studentRepository
                    .findById(chatRoom.getStudentId())
                    .orElseThrow(StudentNotFoundException::new);

    TeacherJpaEntity teacher =
            teacherRepository
                    .findById(chatRoom.getTeacherId())
                    .orElseThrow(TeacherNotFoundException::new);

    CounselRequestJpaEntity counselRequest =
            counselRequestRepository
                    .findById(chatRoom.getCounselRequestId())
                    .orElseThrow(CounselRequestNotFoundException::new);

    ChatRoomJpaEntity entity = chatRoomMapper.toEntity(null, student, teacher, counselRequest);

    return chatRoomMapper.toDomain(chatRoomRepository.save(entity));
  }

  @Override
  @Transactional
  public ChatMessage save(ChatMessage chatMessage) {
    UserJpaEntity user =
            userRepository.findById(chatMessage.getSenderId())
                    .orElseThrow(UserNotFoundException::new);

    ChatRoomJpaEntity chatRoom =
            chatRoomRepository
                    .findById(chatMessage.getChatRoomId())
                    .orElseThrow(ChatRoomNotFoundException::new);

    ChatMessageJpaEntity entity =
            chatMessageMapper.toEntity(chatMessage, user, chatRoom);

    return chatMessageMapper.toDomain(chatMessageRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ChatRoom> loadById(UUID chatRoomId) {
    return chatRoomRepository.findById(chatRoomId).map(chatRoomMapper::toDomain);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ChatMessage> loadByChatRoomId(UUID chatRoomId, Pageable pageable) {
    chatRoomRepository.findById(chatRoomId)
            .orElseThrow(ChatRoomNotFoundException::new);

    return chatMessageRepository.findByChatRoom_Id(chatRoomId, pageable).stream()
            .map(chatMessageMapper::toDomain)
            .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isParticipant(UUID chatRoomId, UUID userId) {
    return chatRoomRepository
            .findById(chatRoomId)
            .map(room ->
                    userId.equals(room.getTeacher().getId())
                            || userId.equals(room.getStudent().getId()))
            .orElse(false);
  }
}
