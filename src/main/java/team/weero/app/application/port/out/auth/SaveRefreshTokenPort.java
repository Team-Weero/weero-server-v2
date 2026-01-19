package team.weero.app.application.port.out.auth;

import team.weero.app.domain.auth.RefreshToken;

public interface SaveRefreshTokenPort {

  void save(RefreshToken refreshToken);
}
