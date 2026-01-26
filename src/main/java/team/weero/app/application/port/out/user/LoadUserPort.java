package team.weero.app.application.port.out.user;

import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.auth.AuthUser;
import team.weero.app.domain.user.model.User;

public interface LoadUserPort {

  Optional<AuthUser> loadByEmail(String email);

  String getEncodedPasswordByEmail(String email);

  Optional<User> loadById(UUID userId);
}
