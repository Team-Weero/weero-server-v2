package team.weero.app.application.service.counsel;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.counsel.dto.request.UpdateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.counsel.ForbiddenCounselRequestAccessException;
import team.weero.app.application.exception.counsel.InvalidCounselRequestStatusException;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.counsel.UpdateCounselRequestUseCase;
import team.weero.app.application.port.out.counsel.CheckCounselRequestOwnerPort;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateCounselRequestService implements UpdateCounselRequestUseCase {

  private final LoadCounselRequestPort loadCounselRequestPort;
  private final SaveCounselRequestPort saveCounselRequestPort;
  private final CheckCounselRequestOwnerPort checkCounselRequestOwnerPort;
  private final LoadStudentPort loadStudentPort;

  @Override
  public CounselRequestResponse execute(UUID id, UpdateCounselRequestRequest request, UUID userId) {
    StudentInfo studentInfo =
        loadStudentPort.loadByUserId(userId).orElseThrow(StudentNotFoundException::new);

    if (!checkCounselRequestOwnerPort.isStudentOwner(id, studentInfo.id())) {
      throw new ForbiddenCounselRequestAccessException();
    }

    CounselRequest existing =
        loadCounselRequestPort.loadById(id).orElseThrow(CounselRequestNotFoundException::new);

    if (!existing.isPending()) {
      throw new InvalidCounselRequestStatusException();
    }

    CounselRequest updated =
        CounselRequest.builder()
            .id(existing.getId())
            .status(existing.getStatus())
            .gender(request.gender() != null ? request.gender() : existing.getGender())
            .hasCounselingExperience(
                request.hasCounselingExperience() != null
                    ? request.hasCounselingExperience()
                    : existing.isHasCounselingExperience())
            .category(request.category() != null ? request.category() : existing.getCategory())
            .studentId(existing.getStudentId())
            .teacherId(request.teacherId() != null ? request.teacherId() : existing.getTeacherId())
            .createdAt(existing.getCreatedAt())
            .updatedAt(existing.getUpdatedAt())
            .deletedAt(existing.getDeletedAt())
            .build();

    CounselRequest saved = saveCounselRequestPort.save(updated);

    return new CounselRequestResponse(
        saved.getId(),
        saved.getStatus(),
        saved.getGender(),
        saved.isHasCounselingExperience(),
        saved.getCategory(),
        saved.getStudentId(),
        saved.getStudentName(),
        saved.getTeacherId(),
        saved.getTeacherName(),
        saved.getCreatedAt(),
        saved.getUpdatedAt());
  }
}
