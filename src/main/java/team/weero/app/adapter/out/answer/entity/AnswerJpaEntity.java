package team.weero.app.adapter.out.answer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_answer")
public class AnswerJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostJpaEntity post;

    private LocalDateTime deletedTime;

    public void markDeleted() {
        this.deletedTime = LocalDateTime.now();
    }
}
