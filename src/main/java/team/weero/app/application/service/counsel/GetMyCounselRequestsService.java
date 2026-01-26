package team.weero.app.application.service.counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.adapter.in.web.student.dto.response.StudentInfo;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.port.in.counsel.GetMyCounselRequestsUseCase;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.domain.counsel.CounselRequest;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyCounselRequestsService implements GetMyCounselRequestsUseCase {

    private final LoadCounselRequestPort loadCounselRequestPort;
    private final LoadStudentPort loadStudentPort;

    @Override
    public CounselRequestListResponse execute(UUID userId) {
        StudentInfo studentInfo =
                loadStudentPort.loadByUserId(userId).orElseThrow(StudentNotFoundException::new);

        List<CounselRequest> counselRequests =
                loadCounselRequestPort.loadAllByStudentId(studentInfo.id());

        List<CounselRequestResponse> responses =
                counselRequests.stream()
                        .map(
                                cr ->
                                        new CounselRequestResponse(
                                                cr.getId(),
                                                cr.getStatus(),
                                                cr.getGender(),
                                                cr.isHasCounselingExperience(),
                                                cr.getCategory(),
                                                cr.getStudentId(),
                                                cr.getStudentName(),
                                                cr.getTeacherId(),
                                                cr.getTeacherName(),
                                                cr.getCreatedAt(),
                                                cr.getUpdatedAt()))
                        .toList();

        return new CounselRequestListResponse(responses);
    }
}
