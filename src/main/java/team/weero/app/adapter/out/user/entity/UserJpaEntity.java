package team.weero.app.adapter.out.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import team.weero.app.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
public class UserJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    private LocalDateTime deletedTime;

    public void markDeleted() {
        this.deletedTime = LocalDateTime.now();
    }
}
