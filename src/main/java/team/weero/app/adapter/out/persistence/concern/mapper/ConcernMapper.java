package team.weero.app.adapter.out.persistence.concern.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.concern.model.Concern;
import team.weero.app.adapter.out.persistence.concern.entity.ConcernJpaEntity;

@Component
public class ConcernMapper {

    public Concern toDomain(ConcernJpaEntity entity) {
        return new Concern(
            entity.getId(),
            entity.getStudentId(),
            entity.getTitle(),
            entity.getContents(),
            entity.isResolved(),
            entity.getCreatedAt()
        );
    }

    public ConcernJpaEntity toEntity(Concern domain) {
        return ConcernJpaEntity.builder()
            .id(domain.getId())
            .studentId(domain.getStudentId())
            .title(domain.getTitle())
            .contents(domain.getContents())
            .isResolved(domain.isResolved())
            .createdAt(domain.getCreatedAt())
            .build();
    }
}
