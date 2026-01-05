package team.weero.app.adapter.out.persistence.teacher.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

import java.util.UUID;

/**
 * Teacher JPA Entity
 * 데이터베이스 테이블과 매핑되는 엔티티
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tbl_teacher")
public class TeacherJpaEntity {

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
    private UserJpaEntity user;

    /**
     * 알림 설정 업데이트
     */
    public void updateNotificationSettings(String startTime, String endTime) {
        this.noNotificationStartTime = startTime;
        this.noNotificationEndTime = endTime;
    }

    /**
     * 디바이스 토큰 업데이트
     */
    public void updateDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
