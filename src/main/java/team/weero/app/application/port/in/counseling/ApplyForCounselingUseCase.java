package team.weero.app.application.port.in.counseling;

import team.weero.app.application.service.counseling.dto.request.CounselingRequest;

public interface ApplyForCounselingUseCase {
    void execute(CounselingRequest request);
}
