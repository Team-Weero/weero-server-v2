package team.weero.app.adapter.out.persistence.counseling.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.counseling.model.CounselingApplication;
import team.weero.app.adapter.out.persistence.counseling.entity.CounselingApplicationJpaEntity;

@Component
public class CounselingMapper {

    public CounselingApplicationJpaEntity toEntity(CounselingApplication domain) {
        return CounselingApplicationJpaEntity.builder()
                .id(domain.getId())
                .studentId(domain.getStudentId())
                .teacherId(domain.getTeacherId())
                .time(domain.getTime())
                .counselDate(domain.getCounselDate())
                .applicationDate(domain.getApplicationDate())
                .location(toPersistenceLocation(domain.getLocation()))
                .isChecked(domain.isChecked())
                .build();
    }

    public CounselingApplication toDomain(CounselingApplicationJpaEntity entity) {
        return CounselingApplication.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .teacherId(entity.getTeacherId())
                .time(entity.getTime())
                .counselDate(entity.getCounselDate())
                .applicationDate(entity.getApplicationDate())
                .location(toDomainLocation(entity.getLocation()))
                .isChecked(entity.isChecked())
                .build();
    }

    private team.weero.app.adapter.out.persistence.counseling.entity.CounselingLocation toPersistenceLocation(
            team.weero.app.domain.counseling.model.CounselingLocation domainLocation) {
        return team.weero.app.adapter.out.persistence.counseling.entity.CounselingLocation.valueOf(domainLocation.name());
    }

    private team.weero.app.domain.counseling.model.CounselingLocation toDomainLocation(
            team.weero.app.adapter.out.persistence.counseling.entity.CounselingLocation persistenceLocation) {
        return team.weero.app.domain.counseling.model.CounselingLocation.valueOf(persistenceLocation.name());
    }
}
