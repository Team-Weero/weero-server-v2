package team.weero.app.adapter.out.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import team.weero.app.domain.student.type.StudentRole;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_student")
public class StudentJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, length = 20)
    private String accountId;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private int classRoom;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentRole role;
}
