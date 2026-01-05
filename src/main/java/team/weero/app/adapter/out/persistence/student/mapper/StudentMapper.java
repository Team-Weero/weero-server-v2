package team.weero.app.adapter.out.persistence.student.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.student.model.Student;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;

@Component
public class StudentMapper {

    public Student toDomain(StudentJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Student.builder()
                .id(entity.getId())
                .name(entity.getName())
                .nickname(entity.getNickname())
                .accountId(entity.getAccountId())
                .gcn(entity.getGcn())
                .role(team.weero.app.domain.student.model.StudentRole.valueOf(entity.getStudentRole().name()))
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .build();
    }

    public StudentJpaEntity toEntity(Student domain) {
        if (domain == null) {
            return null;
        }

        return StudentJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .nickname(domain.getNickname())
                .accountId(domain.getAccountId())
                .gcn(domain.getGcn())
                .studentRole(team.weero.app.adapter.out.persistence.student.entity.StudentRole.valueOf(domain.getRole().name()))
                .build();
    }
}
