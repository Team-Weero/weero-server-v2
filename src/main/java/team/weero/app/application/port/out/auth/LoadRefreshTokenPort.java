package team.weero.app.application.port.out.auth;

import java.util.Optional;
import team.weero.app.domain.auth.RefreshToken;

public interface LoadRefreshTokenPort {

  Optional<RefreshToken> loadByToken(String token);

  void deleteByToken(String token);
}
