package team.weero.app.adapter.out.notice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_notice")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String title;

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserJpaEntity user;

  @Column(columnDefinition = "DATETIME")
  private LocalDateTime deletedAt;

  public void markDeleted() {
    this.deletedAt = LocalDateTime.now();
  }
}
