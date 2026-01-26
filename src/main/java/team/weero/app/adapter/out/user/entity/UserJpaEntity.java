package team.weero.app.adapter.out.user.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String email;

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Authority authority;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private StudentJpaEntity student;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private TeacherJpaEntity teacher;

  private LocalDateTime deletedAt;

  public void markDeleted() {
    this.deletedAt = LocalDateTime.now();
  }
}
