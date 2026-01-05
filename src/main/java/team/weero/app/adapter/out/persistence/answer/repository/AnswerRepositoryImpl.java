package team.weero.app.adapter.out.persistence.answer.repository;

import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.persistence.answer.entity.AnswerJpaEntity;
import team.weero.app.adapter.out.persistence.answer.mapper.AnswerMapper;
import team.weero.app.application.port.out.answer.AnswerRepository;
import team.weero.app.domain.answer.model.Answer;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.adapter.out.persistence.concern.entity.ConcernJpaEntity;
import team.weero.app.adapter.out.persistence.concern.repository.ConcernJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private final AnswerJpaRepository answerJpaRepository;
    private final AnswerMapper answerMapper;
    private final ConcernJpaRepository concernRepository;
    private final StudentJpaRepository studentJpaRepository;

    public AnswerRepositoryImpl(AnswerJpaRepository answerJpaRepository, AnswerMapper answerMapper,
                                ConcernJpaRepository concernRepository, StudentJpaRepository studentJpaRepository) {
        this.answerJpaRepository = answerJpaRepository;
        this.answerMapper = answerMapper;
        this.concernRepository = concernRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public Answer save(Answer answer) {
        AnswerJpaEntity entity = answerMapper.toEntity(answer);
        AnswerJpaEntity savedEntity = answerJpaRepository.save(entity);
        return answerMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Answer> findById(UUID id) {
        return answerJpaRepository.findById(id)
                .map(answerMapper::toDomain);
    }

    @Override
    public List<Answer> findByConcernId(UUID concernId) {
        ConcernJpaEntity concern = concernRepository.findById(concernId)
                .orElseThrow(() -> new RuntimeException("Concern not found"));
        return answerJpaRepository.findByConcernOrderByCreatedAtAsc(concern).stream()
                .map(answerMapper::toDomain)
                .toList();
    }

    @Override
    public List<Answer> findByStudentId(UUID studentId) {
        StudentJpaEntity student = studentJpaRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("StudentJpaEntity not found"));
        return answerJpaRepository.findByStudentOrderByCreatedAtDesc(student).stream()
                .map(answerMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        answerJpaRepository.deleteById(id);
    }

    @Override
    public int countByConcernId(UUID concernId) {
        ConcernJpaEntity concern = concernRepository.findById(concernId)
                .orElseThrow(() -> new RuntimeException("Concern not found"));
        return answerJpaRepository.countByConcern(concern);
    }
}
