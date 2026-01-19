package team.weero.app.application.port.in.auth;

import team.weero.app.adapter.in.web.auth.dto.response.TokenResponse;

public interface ReissueTokenUseCase {

  TokenResponse execute(String refreshToken);
}
