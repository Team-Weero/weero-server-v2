package team.weero.app.core.counseling.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.counseling.dto.request.CounselingRequest;
import team.weero.app.core.counseling.service.CommandCounselingService;

@Usecase
@RequiredArgsConstructor
public class ApplyForCounselingUseCase {
    private final CommandCounselingService commandCounselingService;

    public void execute(CounselingRequest request) {
       commandCounselingService.applyForCounseling(request);
    }
}
