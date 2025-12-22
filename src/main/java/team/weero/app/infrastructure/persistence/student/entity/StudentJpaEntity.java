package team.weero.app.infrastructure.persistence.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.infrastructure.persistence.user.entity.UserJpaEntity;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_student")
public class StudentJpaEntity {
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
    private StudentRole studentRole;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;
}
