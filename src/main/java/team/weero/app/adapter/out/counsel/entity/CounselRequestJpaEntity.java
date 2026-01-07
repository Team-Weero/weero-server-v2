package team.weero.app.adapter.out.counsel.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
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
}
