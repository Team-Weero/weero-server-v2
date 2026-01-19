package team.weero.app.application.port.out.user;

import java.util.Optional;
import team.weero.app.domain.auth.AuthUser;

public interface LoadUserPort {

  Optional<AuthUser> loadByEmail(String email);

  String getEncodedPasswordByEmail(String email);
}
