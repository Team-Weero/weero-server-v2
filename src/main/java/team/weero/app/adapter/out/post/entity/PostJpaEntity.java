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

  private LocalDateTime deletedAt;

  public void markDeleted() {
    this.deletedAt = LocalDateTime.now();
  }

  public void update(String title, String content) {
    if (title != null) this.title = title;
    if (content != null) this.content = content;
  }
}
