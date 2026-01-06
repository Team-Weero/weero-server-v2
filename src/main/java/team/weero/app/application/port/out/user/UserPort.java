package team.weero.app.application.port.out.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.user.model.User;
import team.weero.app.domain.user.model.UserRole;

public interface UserPort {

  User save(User user);

  Optional<User> findById(UUID id);

  List<User> findByUserRole(UserRole role);

  List<User> findAll();

  void deleteById(UUID id);

  boolean existsById(UUID id);
}
