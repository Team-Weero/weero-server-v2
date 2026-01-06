package team.weero.app.adapter.out.persistence.user.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.domain.user.model.User;

@Component
public class UserMapper {

  public UserJpaEntity toEntity(User user) {
    if (user == null) {
      return null;
    }

    return UserJpaEntity.builder()
        .id(user.getId())
        .password(user.getPassword())
        .userRole(user.getRole())
        .build();
  }

  public User toDomain(UserJpaEntity entity) {
    if (entity == null) {
      return null;
    }

    return User.builder()
        .id(entity.getId())
        .password(entity.getPassword())
        .role(entity.getUserRole())
        .build();
  }
}
