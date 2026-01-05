package team.weero.app.adapter.out.persistence.user.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.user.model.User;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

/**
 * User Mapper (Infrastructure Layer)
 * Converts between Domain Model and JPA Entity
 */
@Component
public class UserMapper {

    /**
     * Convert Domain User to JPA Entity
     * @param user Domain user
     * @return JPA entity
     */
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

    /**
     * Convert JPA Entity to Domain User
     * @param entity JPA entity
     * @return Domain user
     */
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
