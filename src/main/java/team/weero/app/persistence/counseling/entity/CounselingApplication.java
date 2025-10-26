package team.weero.app.persistence.counseling.entity;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.counseling.type.CounselingLocation;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_counseling_application")
@Setter
public class CounselingApplication {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(nullable = false)
    private boolean isChecked;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private LocalDate counselDate;

    @Column(nullable = false)
    private LocalDate applicationDate;

    @Column(nullable = false)
    private CounselingLocation location;
}