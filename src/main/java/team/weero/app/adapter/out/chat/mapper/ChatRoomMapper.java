package team.weero.app.adapter.out.chat.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.chat.ChatRoom;

@Component
@RequiredArgsConstructor
public class ChatRoomMapper {

  public ChatRoomJpaEntity toEntity(
      ChatRoom chatRoom,
      StudentJpaEntity student,
      TeacherJpaEntity teacher,
      CounselRequestJpaEntity counselRequest) {
    return ChatRoomJpaEntity.builder()
        .student(student)
        .teacher(teacher)
        .counselRequest(counselRequest)
        .build();
  }

  public ChatRoom toDomain(ChatRoomJpaEntity entity) {
    return ChatRoom.builder()
        .id(entity.getId())
        .counselRequestId(entity.getCounselRequest().getId())
        .teacherId(entity.getTeacher().getId())
        .studentId(entity.getStudent().getId())
        .createdAt(entity.getCreatedAt())
        .build();
  }
}
