package team.weero.app.presentation.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.weero.app.core.auth.dto.request.LoginRequest;
import team.weero.app.core.auth.dto.request.SignupRequest;
import team.weero.app.core.auth.dto.response.TokenResponse;
import team.weero.app.core.auth.usecase.LoginUseCase;
import team.weero.app.core.auth.usecase.SignupUseCase;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthWebAdapter {

    private final SignupUseCase signupUsecase;
    private final LoginUseCase loginUsecase;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Valid SignupRequest request) {
        signupUsecase.execute(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginUsecase.execute(request);
    }
}
