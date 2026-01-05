package team.weero.app.infrastructure.persistence.counseling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_counseling_application")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CounselingApplicationJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "student_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID studentId;

    @Column(name = "teacher_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID teacherId;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private LocalDate counselDate;

    @Column(nullable = false)
    private LocalDate applicationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CounselingLocation location;

    @Column(nullable = false)
    private boolean isChecked;
}
