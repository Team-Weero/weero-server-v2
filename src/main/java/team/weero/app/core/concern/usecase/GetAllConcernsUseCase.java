package team.weero.app.core.concern.usecase;

import team.weero.app.core.Usecase;
import team.weero.app.core.concern.dto.response.ConcernResponse;
import team.weero.app.core.concern.service.ConcernService;

import java.util.List;

@Usecase
public class GetAllConcernsUseCase {

    private final ConcernService concernService;

    public GetAllConcernsUseCase(ConcernService concernService) {
        this.concernService = concernService;
    }

    public List<ConcernResponse> execute() {
        return concernService.getAllConcerns();
    }
}
