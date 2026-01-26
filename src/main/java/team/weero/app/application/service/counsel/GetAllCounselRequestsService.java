package team.weero.app.application.service.counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.application.port.in.counsel.GetAllCounselRequestsUseCase;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.domain.counsel.CounselRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllCounselRequestsService implements GetAllCounselRequestsUseCase {

    private final LoadCounselRequestPort loadCounselRequestPort;

    @Override
    public CounselRequestListResponse execute() {
        List<CounselRequest> counselRequests = loadCounselRequestPort.loadAll();

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
