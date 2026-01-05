package team.weero.app.adapter.out.persistence.counseling.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.weero.app.domain.counseling.model.CounselingApplication;
import team.weero.app.application.port.out.counseling.CounselingRepository;
import team.weero.app.adapter.out.persistence.counseling.mapper.CounselingMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CounselingRepositoryImpl implements CounselingRepository {

    private final CounselingJpaRepository counselingJpaRepository;
    private final CounselingMapper counselingMapper;

    @Override
    public CounselingApplication save(CounselingApplication counselingApplication) {
        var entity = counselingMapper.toEntity(counselingApplication);
        var savedEntity = counselingJpaRepository.save(entity);
        return counselingMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<CounselingApplication> findById(UUID id) {
        return counselingJpaRepository.findById(id)
                .map(counselingMapper::toDomain);
    }

    @Override
    public List<CounselingApplication> findByStudentId(UUID studentId) {
        return counselingJpaRepository.findByStudentId(studentId).stream()
                .map(counselingMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CounselingApplication> findByTeacherId(UUID teacherId) {
        return counselingJpaRepository.findByTeacherId(teacherId).stream()
                .map(counselingMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByTeacherIdAndCounselDateAndTime(UUID teacherId, LocalDate counselDate, LocalTime time) {
        return counselingJpaRepository.existsByTeacherIdAndCounselDateAndTime(teacherId, counselDate, time);
    }

    @Override
    public void deleteById(UUID id) {
        counselingJpaRepository.deleteById(id);
    }
}
