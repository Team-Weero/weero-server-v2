package team.weero.app.presentation.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.weero.app.application.auth.dto.request.LoginRequest;
import team.weero.app.application.auth.dto.request.RefreshTokenRequest;
import team.weero.app.application.auth.dto.request.SignupRequest;
import team.weero.app.application.auth.dto.response.TokenResponse;
import team.weero.app.application.auth.usecase.LoginUseCase;
import team.weero.app.application.auth.usecase.RefreshUseCase;
import team.weero.app.application.auth.usecase.SignupUseCase;

/**
 * Auth Controller
 * Presentation layer controller for authentication endpoints
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignupUseCase signupUseCase;
    private final LoginUseCase loginUseCase;
    private final RefreshUseCase refreshUseCase;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Valid SignupRequest request) {
        signupUseCase.execute(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginUseCase.execute(request);
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestBody @Valid RefreshTokenRequest request) {
        return refreshUseCase.execute(request);
    }
}
