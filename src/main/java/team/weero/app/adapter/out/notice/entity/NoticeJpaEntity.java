package team.weero.app.adapter.out.notice.entity;

import jakarta.persistence.*;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_notice")
public class NoticeJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    private LocalDateTime deletedTime;

    public void markDeleted() {
        this.deletedTime = LocalDateTime.now();
    }
}
