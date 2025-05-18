package team.weero.app.persistence.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.student.type.Role;
import team.weero.app.persistence.user.entity.User;

import java.util.UUID;

@Entity
@Getter
@Table(name = "tbl_student")
public class Student {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private int grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role studentRole;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}