package team.weero.app.core.concern.usecase;

import team.weero.app.core.Usecase;
import team.weero.app.core.concern.dto.request.CreateConcernRequest;
import team.weero.app.core.concern.service.ConcernService;

@Usecase
public class CreateConcernUseCase {

    private final ConcernService concernService;

    public CreateConcernUseCase(ConcernService concernService) {
        this.concernService = concernService;
    }

    public void execute(CreateConcernRequest request, String accountId) {
        concernService.createConcern(request, accountId);
    }
}
