package team.weero.app.adapter.out.post.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(100)")
  private String title;

  @Column(nullable = false, columnDefinition = "VARCHAR(10000)")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", nullable = false)
  private StudentJpaEntity student;

  private LocalDateTime deletedTime;

  public void markDeleted() {
    this.deletedTime = LocalDateTime.now();
  }
}
