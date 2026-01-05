package team.weero.app.adapter.out.persistence.answer.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.answer.entity.AnswerJpaEntity;
import team.weero.app.domain.answer.model.Answer;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.adapter.out.persistence.concern.entity.ConcernJpaEntity;
import team.weero.app.adapter.out.persistence.concern.repository.ConcernJpaRepository;

@Component
public class AnswerMapper {

    private final ConcernJpaRepository concernRepository;
    private final StudentJpaRepository studentJpaRepository;

    public AnswerMapper(ConcernJpaRepository concernRepository, StudentJpaRepository studentJpaRepository) {
        this.concernRepository = concernRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public Answer toDomain(AnswerJpaEntity entity) {
        return Answer.builder()
                .id(entity.getId())
                .concernId(entity.getConcern().getId())
                .studentId(entity.getStudent().getId())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public AnswerJpaEntity toEntity(Answer domain) {
        ConcernJpaEntity concern = concernRepository.findById(domain.getConcernId())
                .orElseThrow(() -> new RuntimeException("Concern not found"));
        StudentJpaEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElseThrow(() -> new RuntimeException("StudentJpaEntity not found"));

        return AnswerJpaEntity.builder()
                .id(domain.getId())
                .concern(concern)
                .student(student)
                .content(domain.getContent())
                .createdAt(domain.getCreatedAt())
                .build();
    }
}
