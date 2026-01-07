package team.weero.app.adapter.out.teacher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import team.weero.app.global.entity.BaseTimeEntity;

import java.time.LocalTime;

@Entity
@Table(name = "tbl_teacher")
public class TeacherJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 255)
    private String deviceToken;

    @Column(nullable = false)
    private LocalTime noNotificationStartTime;

    @Column(nullable = false)
    private LocalTime noNotificationEndTime;
}
