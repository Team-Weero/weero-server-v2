package team.weero.app.infrastructure.persistence.answer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.infrastructure.persistence.concern.entity.ConcernJpaEntity;
import team.weero.app.infrastructure.persistence.student.entity.StudentJpaEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tbl_answer")
public class AnswerJpaEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "concern_id")
    private ConcernJpaEntity concern;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentJpaEntity student;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
