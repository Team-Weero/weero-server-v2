package team.weero.app.adapter.out.heart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;

@Entity
@Table(
    name = "answer_heart",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"answer_id", "user_id"})})
public class AnswerHeartJpaEntity {

  @Id @GeneratedValue private UUID id;

  @Column(nullable = false)
  private UUID answerId;

  @Column(nullable = false)
  private UUID userId;

  public static AnswerHeartJpaEntity of(UUID answerId, UUID userId) {
    AnswerHeartJpaEntity answerHeart = new AnswerHeartJpaEntity();
    answerHeart.answerId = answerId;
    answerHeart.userId = userId;
    return answerHeart;
  }
}
