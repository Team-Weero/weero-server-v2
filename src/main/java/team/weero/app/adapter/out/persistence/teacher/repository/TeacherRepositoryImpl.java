package team.weero.app.infrastructure.persistence.teacher.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.domain.teacher.repository.TeacherRepository;
import team.weero.app.infrastructure.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.infrastructure.persistence.teacher.mapper.TeacherMapper;

import java.util.Optional;
import java.util.UUID;

/**
 * Teacher Repository 구현체
 * 도메인 Repository 인터페이스를 구현하는 Infrastructure 계층
 */
@Repository
@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {

    private final TeacherJpaRepository teacherJpaRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public Optional<Teacher> findByAccountId(String accountId) {
        return teacherJpaRepository.findByAccountId(accountId)
                .map(teacherMapper::toDomain);
    }

    @Override
    public Optional<Teacher> findById(UUID id) {
        return teacherJpaRepository.findById(id)
                .map(teacherMapper::toDomain);
    }

    @Override
    public Optional<Teacher> findByUserId(UUID userId) {
        return teacherJpaRepository.findAll().stream()
                .filter(entity -> entity.getUser() != null && entity.getUser().getId().equals(userId))
                .findFirst()
                .map(teacherMapper::toDomain);
    }

    @Override
    public Teacher save(Teacher teacher) {
        TeacherJpaEntity entity = teacherJpaRepository.findById(teacher.getId())
                .orElseThrow(() -> new IllegalStateException("Teacher entity not found for update"));

        teacherMapper.updateEntity(teacher, entity);
        TeacherJpaEntity savedEntity = teacherJpaRepository.save(entity);
        return teacherMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByAccountId(String accountId) {
        return teacherJpaRepository.existsByAccountId(accountId);
    }
}
