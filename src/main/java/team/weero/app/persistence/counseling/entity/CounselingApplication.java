package team.weero.app.persistence.counseling.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.counseling.type.Period;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tbl_counseling_application")
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Period period;

    @Column(nullable = false)
    private LocalDate counselDate;

    @Column(nullable = false)
    private LocalDate applicationDate;
}