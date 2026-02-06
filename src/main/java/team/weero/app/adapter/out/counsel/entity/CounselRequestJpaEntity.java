package team.weero.app.adapter.out.counsel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.counsel.type.Gender;
import team.weero.app.domain.counsel.type.Status;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_counsel_request")
@Getter
@Builder
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

  public void update(
      Status status,
      Gender gender,
      Boolean hasCounselingExperience,
      String category,
      TeacherJpaEntity teacher) {
    if (status != null) this.status = status;
    if (gender != null) this.gender = gender;
    if (hasCounselingExperience != null) this.hasCounselingExperience = hasCounselingExperience;
    if (category != null) this.category = category;
    if (teacher != null) this.teacher = teacher;
  }
}
