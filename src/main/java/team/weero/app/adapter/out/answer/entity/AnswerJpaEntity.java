package team.weero.app.adapter.out.answer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import team.weero.app.domain.BaseTimeEntity;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;

@Entity
@Table(name = "tbl_answer")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
  private String answer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserJpaEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private PostJpaEntity post;

  private LocalDateTime deletedAt;

  public void markDeleted() {
    this.deletedAt = LocalDateTime.now();
  }
}
