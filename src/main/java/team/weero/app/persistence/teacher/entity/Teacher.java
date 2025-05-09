package team.weero.app.persistence.teacher.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.user.entity.User;

import java.util.UUID;

@Entity
@Table(name = "tbl_teacher")
public class Teacher {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 255)
    private String deviceToken;

    @Column(nullable = false, length = 255)
    private String noNotificationStartTime;

    @Column(nullable = false, length = 255)
    private String noNotificationEndTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}