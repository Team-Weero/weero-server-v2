package team.weero.app.adapter.out.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.chat.mapper.ChatRoomMapper;
import team.weero.app.adapter.out.chat.repository.ChatRoomRepository;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.adapter.out.counsel.repository.CounselRequestRepository;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.out.chat.SaveChatRoomPort;
import team.weero.app.domain.chat.ChatRoom;

@Component
@RequiredArgsConstructor
public class ChatPersistenceAdapter implements SaveChatRoomPort {

    private final ChatRoomRepository chatRoomRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CounselRequestRepository counselRequestRepository;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        StudentJpaEntity student = studentRepository.findById(chatRoom.getStudentId())
                .orElseThrow(StudentNotFoundException::new);
        TeacherJpaEntity teacher = teacherRepository.findById(chatRoom.getTeacherId())
                .orElseThrow(TeacherNotFoundException::new);
        CounselRequestJpaEntity counselRequest = counselRequestRepository.findById(chatRoom.getCounselRequestId())
                .orElseThrow(CounselRequestNotFoundException::new);

        ChatRoomJpaEntity entity = chatRoomMapper.toEntity(null, student, teacher, counselRequest);
        return chatRoomMapper.toDomain(chatRoomRepository.save(entity));
    }
}
