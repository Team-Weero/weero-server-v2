package team.weero.app.core.concern.usecase;

import team.weero.app.core.Usecase;
import team.weero.app.core.concern.dto.response.ConcernResponse;
import team.weero.app.core.concern.service.ConcernService;

import java.util.UUID;

@Usecase
public class GetConcernByIdUseCase {

    private final ConcernService concernService;

    public GetConcernByIdUseCase(ConcernService concernService) {
        this.concernService = concernService;
    }

    public ConcernResponse execute(UUID id) {
        return concernService.getConcernById(id);
    }
}
