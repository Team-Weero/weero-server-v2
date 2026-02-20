package team.weero.app.adapter.out.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.BaseTimeEntity;

@Entity
@Getter
@SuperBuilder
@Table(name = "tbl_chat_room")
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomJpaEntity extends BaseTimeEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", nullable = false)
  private StudentJpaEntity student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", nullable = false)
  private TeacherJpaEntity teacher;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "counsel_id")
  private CounselRequestJpaEntity counselRequest;
}
