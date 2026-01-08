package team.weero.app.adapter.out.counsel.entity;


import jakarta.persistence.*;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.counsel.type.Gender;
import team.weero.app.domain.counsel.type.Status;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_counsel_request")
public class CounselRequestJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String accessPassword;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private boolean hasCounselingExperience;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentJpaEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherJpaEntity teacher;
}
