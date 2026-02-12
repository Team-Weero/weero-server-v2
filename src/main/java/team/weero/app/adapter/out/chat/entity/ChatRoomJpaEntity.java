package team.weero.app.adapter.out.chat.entity;

import jakarta.persistence.*;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.BaseTimeEntity;

@Entity
@Table(name = "tbl_chat_room")
public class ChatRoomJpaEntity extends BaseTimeEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", nullable = false)
  private StudentJpaEntity student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", nullable = false)
  private TeacherJpaEntity teacher;
}
