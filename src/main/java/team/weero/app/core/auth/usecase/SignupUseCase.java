package team.weero.app.core.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.auth.dto.request.SignupRequest;
import team.weero.app.core.auth.service.CommandAuthService;

@Usecase
@RequiredArgsConstructor
public class SignupUseCase {

    private final CommandAuthService commandAuthService;

    public void execute(SignupRequest request) {
        commandAuthService.signup(request);
    }

}
