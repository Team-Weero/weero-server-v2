package team.weero.app.adapter.out.heart.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
/**
 * 한 유저는 한 게시글에 좋아요를 딱 한 번만 하기 위해
 */
@Table(
        name = "post_heart",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "user_id"})
        }
)
public class HeartJpaEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID postId;

    @Column(nullable = false)
    private UUID userId;
}
