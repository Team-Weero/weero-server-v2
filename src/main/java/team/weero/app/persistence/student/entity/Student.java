package team.weero.app.persistence.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.student.type.Role;
import team.weero.app.persistence.user.entity.User;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false, unique = true, length = 30)
    private String accountId;

    @Column(nullable = false)
    private String gcn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role studentRole;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Role getStudentRole() {
        return studentRole;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getGcn() {
        return gcn;
    }

    public User getUser() {
        return user;
    }
}
