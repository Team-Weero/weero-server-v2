package team.weero.app.core.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.auth.dto.request.LoginRequest;
import team.weero.app.core.auth.dto.response.TokenResponse;
import team.weero.app.core.auth.service.CommandAuthService;

@Usecase
@RequiredArgsConstructor
public class LoginUseCase {

    private final CommandAuthService commandAuthService;

    public TokenResponse execute(LoginRequest request) {
        return commandAuthService.login(request);
    }

}
