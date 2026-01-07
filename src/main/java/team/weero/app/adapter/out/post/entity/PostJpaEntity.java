package team.weero.app.adapter.out.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import team.weero.app.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_post")
public class PostJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(nullable = false, columnDefinition = "VARCHAR(10000)")
    private String content;

    private LocalDateTime deletedTime;

    public void markDeleted() {
        this.deletedTime = LocalDateTime.now();
    }
}
