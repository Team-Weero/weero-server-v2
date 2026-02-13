package team.weero.app.application.port.in.auth;

import team.weero.app.application.port.in.auth.dto.response.TokenInfo;

public interface ReissueTokenUseCase {

  TokenInfo execute(String refreshToken);
}
