package team.weero.app.adapter.out.teacher.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_teacher")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(10)")
  private String name;

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String deviceToken;

  @Column(nullable = false)
  private LocalTime noNotificationStartTime;

  @Column(nullable = false)
  private LocalTime noNotificationEndTime;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserJpaEntity user;
}
