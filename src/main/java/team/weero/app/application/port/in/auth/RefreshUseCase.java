package team.weero.app.application.port.in.auth;

import team.weero.app.application.service.auth.dto.request.RefreshTokenRequest;
import team.weero.app.application.service.auth.dto.response.TokenResponse;

public interface RefreshUseCase {
    TokenResponse execute(RefreshTokenRequest request);
}
