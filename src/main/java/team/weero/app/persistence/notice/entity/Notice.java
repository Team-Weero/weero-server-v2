package team.weero.app.persistence.notice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.user.entity.User;

import java.util.UUID;

@Entity
@Table(name = "tbl_notice")
public class Notice {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 10000)
    private String contents;
}