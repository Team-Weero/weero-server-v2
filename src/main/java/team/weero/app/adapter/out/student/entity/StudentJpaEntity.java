package team.weero.app.adapter.out.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.domain.student.type.StudentRole;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_student")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(20)")
  private String accountId;

  @Column(nullable = false, columnDefinition = "VARCHAR(10)")
  private String name;

  @Column(nullable = false, columnDefinition = "VARCHAR(20)")
  private String nickname;

  @Column(nullable = false)
  private int grade;

  @Column(nullable = false)
  private int classRoom;

  @Column(nullable = false)
  private int number;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StudentRole role;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserJpaEntity user;
}
