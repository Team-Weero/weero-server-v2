package team.weero.app.adapter.out.user.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.domain.user.model.User;

@Component
public class UserMapper {

  public User toDomain(UserJpaEntity entity) {
    return User.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .password(entity.getPassword())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .deletedAt(entity.getDeletedAt())
        .build();
  }

  public static UserJpaEntity toEntity(User user) {
    return UserJpaEntity.builder().email(user.getEmail()).password(user.getPassword()).build();
  }
}
