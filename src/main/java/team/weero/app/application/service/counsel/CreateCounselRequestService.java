package team.weero.app.application.service.counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.counsel.dto.request.CreateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.counsel.CreateCounselRequestUseCase;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.counsel.CounselRequest;
import team.weero.app.domain.counsel.type.Status;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateCounselRequestService implements CreateCounselRequestUseCase {

    private final SaveCounselRequestPort saveCounselRequestPort;
    private final LoadStudentPort loadStudentPort;

    @Override
    public CounselRequestResponse execute(CreateCounselRequestRequest request, UUID userId) {
        StudentInfo studentInfo =
                loadStudentPort.loadByUserId(userId).orElseThrow(StudentNotFoundException::new);

        CounselRequest counselRequest =
                CounselRequest.builder()
                        .accessPassword(request.accessPassword())
                        .status(Status.PENDING)
                        .gender(request.gender())
                        .hasCounselingExperience(request.hasCounselingExperience())
                        .category(request.category())
                        .studentId(studentInfo.id())
                        .teacherId(request.teacherId())
                        .build();

        CounselRequest saved = saveCounselRequestPort.save(counselRequest);

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
