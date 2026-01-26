package team.weero.app.application.service.counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.teacher.dto.response.TeacherInfo;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.counsel.ForbiddenCounselRequestAccessException;
import team.weero.app.application.exception.counsel.InvalidCounselRequestStatusException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.RejectCounselRequestUseCase;
import team.weero.app.application.port.out.counsel.CheckCounselRequestOwnerPort;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.domain.counsel.CounselRequest;
import team.weero.app.domain.counsel.type.Status;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RejectCounselRequestService implements RejectCounselRequestUseCase {

    private final LoadCounselRequestPort loadCounselRequestPort;
    private final SaveCounselRequestPort saveCounselRequestPort;
    private final CheckCounselRequestOwnerPort checkCounselRequestOwnerPort;
    private final LoadTeacherPort loadTeacherPort;

    @Override
    public void execute(UUID id, UUID userId) {
        TeacherInfo teacherInfo =
                loadTeacherPort.loadByUserId(userId).orElseThrow(TeacherNotFoundException::new);

        if (!checkCounselRequestOwnerPort.isTeacherOwner(id, teacherInfo.id())) {
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
                        .accessPassword(existing.getAccessPassword())
                        .status(Status.CANCELLED)
                        .gender(existing.getGender())
                        .hasCounselingExperience(existing.isHasCounselingExperience())
                        .category(existing.getCategory())
                        .studentId(existing.getStudentId())
                        .teacherId(existing.getTeacherId())
                        .createdAt(existing.getCreatedAt())
                        .updatedAt(existing.getUpdatedAt())
                        .deletedTime(existing.getDeletedTime())
                        .build();

        saveCounselRequestPort.save(updated);
    }
}
