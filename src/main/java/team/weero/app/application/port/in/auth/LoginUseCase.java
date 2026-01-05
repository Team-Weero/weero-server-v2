package team.weero.app.application.port.in.auth;

import team.weero.app.application.service.auth.dto.request.LoginRequest;
import team.weero.app.application.service.auth.dto.response.TokenResponse;

public interface LoginUseCase {
    TokenResponse execute(LoginRequest request);
}
