package team.weero.app.application.port.out;

import team.weero.app.domain.auth.RefreshToken;

public interface SaveRefreshTokenPort {

  void save(RefreshToken refreshToken);
}
