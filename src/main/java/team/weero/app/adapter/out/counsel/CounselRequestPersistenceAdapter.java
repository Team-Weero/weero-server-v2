package team.weero.app.adapter.out.counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.adapter.out.counsel.mapper.CounselRequestMapper;
import team.weero.app.adapter.out.counsel.repository.CounselRequestRepository;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.counsel.ForbiddenCounselRequestAccessException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.out.counsel.CheckCounselRequestOwnerPort;
import team.weero.app.application.port.out.counsel.DeleteCounselRequestPort;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.domain.counsel.CounselRequest;
import team.weero.app.domain.counsel.type.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CounselRequestPersistenceAdapter
        implements SaveCounselRequestPort, LoadCounselRequestPort, DeleteCounselRequestPort,
                CheckCounselRequestOwnerPort {

    private final CounselRequestRepository counselRequestRepository;
    private final CounselRequestMapper counselRequestMapper;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public CounselRequest save(CounselRequest counselRequest) {
        StudentJpaEntity student =
                studentRepository
                        .findById(counselRequest.getStudentId())
                        .orElseThrow(StudentNotFoundException::new);

        TeacherJpaEntity teacher =
                teacherRepository
                        .findById(counselRequest.getTeacherId())
                        .orElseThrow(TeacherNotFoundException::new);

        CounselRequestJpaEntity entity;

        if (counselRequest.getId() == null) {
            entity = CounselRequestMapper.toEntity(counselRequest, student, teacher);
            entity = counselRequestRepository.save(entity);
        } else {
            entity =
                    counselRequestRepository
                            .findByIdAndDeletedTimeIsNull(counselRequest.getId())
                            .orElseThrow(CounselRequestNotFoundException::new);

            entity.update(
                    counselRequest.getAccessPassword(),
                    counselRequest.getStatus(),
                    counselRequest.getGender(),
                    counselRequest.isHasCounselingExperience(),
                    counselRequest.getCategory(),
                    teacher);

            entity = counselRequestRepository.save(entity);
        }

        CounselRequestJpaEntity savedEntity = entity;
        return counselRequestMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<CounselRequest> loadById(UUID id) {
        return counselRequestRepository
                .findByIdAndDeletedTimeIsNull(id)
                .map(counselRequestMapper::toDomain);
    }

    @Override
    public List<CounselRequest> loadAllByStudentId(UUID studentId) {
        return counselRequestRepository
                .findAllByStudentIdAndDeletedTimeIsNullOrderByCreatedAtDesc(studentId)
                .stream()
                .map(counselRequestMapper::toDomain)
                .toList();
    }

    @Override
    public List<CounselRequest> loadAllByTeacherId(UUID teacherId) {
        return counselRequestRepository
                .findAllByTeacherIdAndDeletedTimeIsNullOrderByCreatedAtDesc(teacherId)
                .stream()
                .map(counselRequestMapper::toDomain)
                .toList();
    }

    @Override
    public List<CounselRequest> loadAll() {
        return counselRequestRepository.findAllByDeletedTimeIsNullOrderByCreatedAtDesc().stream()
                .map(counselRequestMapper::toDomain)
                .toList();
    }

    @Override
    public List<CounselRequest> loadCompletedBeforeDays(int days) {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
        return counselRequestRepository
                .findAllByStatusAndDeletedTimeIsNullAndUpdatedAtBefore(Status.COMPLETED, cutoffDate)
                .stream()
                .map(counselRequestMapper::toDomain)
                .toList();
    }

    @Override
    public void softDelete(UUID id, UUID userId) {
        CounselRequestJpaEntity counselRequest =
                counselRequestRepository
                        .findByIdAndDeletedTimeIsNull(id)
                        .orElseThrow(CounselRequestNotFoundException::new);

        if (!counselRequest.getStudent().getUser().getId().equals(userId)) {
            throw new ForbiddenCounselRequestAccessException();
        }

        counselRequest.markDeleted();
        counselRequestRepository.save(counselRequest);
    }

    @Override
    public boolean isStudentOwner(UUID counselRequestId, UUID studentId) {
        CounselRequestJpaEntity counselRequest =
                counselRequestRepository
                        .findByIdAndDeletedTimeIsNull(counselRequestId)
                        .orElseThrow(CounselRequestNotFoundException::new);

        return counselRequest.getStudent().getId().equals(studentId);
    }

    @Override
    public boolean isTeacherOwner(UUID counselRequestId, UUID teacherId) {
        CounselRequestJpaEntity counselRequest =
                counselRequestRepository
                        .findByIdAndDeletedTimeIsNull(counselRequestId)
                        .orElseThrow(CounselRequestNotFoundException::new);

        return counselRequest.getTeacher().getId().equals(teacherId);
    }
}
