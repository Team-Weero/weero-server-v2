package team.weero.app.adapter.out.counsel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import team.weero.app.domain.BaseTimeEntity;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.counsel.type.Gender;
import team.weero.app.domain.counsel.type.Status;

@Entity
@Table(name = "tbl_counsel_request")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CounselRequestJpaEntity extends BaseTimeEntity {

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "VARCHAR(20)")
  private Status status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "VARCHAR(20)")
  private Gender gender;

  @Column(nullable = false, columnDefinition = "BOOLEAN")
  private boolean hasCounselingExperience;

  @Column(nullable = false, columnDefinition = "VARCHAR(20)")
  private String category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", nullable = false)
  private StudentJpaEntity student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", nullable = false)
  private TeacherJpaEntity teacher;

  @Column(columnDefinition = "DATETIME")
  private LocalDateTime deletedAt;

  public void markDeleted() {
    this.deletedAt = LocalDateTime.now();
  }
}
