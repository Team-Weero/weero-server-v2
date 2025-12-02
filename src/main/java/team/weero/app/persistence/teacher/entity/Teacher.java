package team.weero.app.persistence.teacher.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.user.entity.User;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tbl_teacher")
public class Teacher {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true, length = 30)
    private String accountId;

    @Column(nullable = true, length = 255)
    private String deviceToken;

    @Column(nullable = false, length = 255)
    private String noNotificationStartTime;

    @Column(nullable = false, length = 255)
    private String noNotificationEndTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updateNotificationSettings(String startTime, String endTime) {
        this.noNotificationStartTime = startTime;
        this.noNotificationEndTime = endTime;
    }

    public void updateDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}