package team.weero.app.domain.user.model;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    private UUID id;
    private String password;
    private UserRole role;

    public static User create(String password, UserRole role) {
        return User.builder()
                .id(UUID.randomUUID())
                .password(password)
                .role(role)
                .build();
    }

    public User updatePassword(String newPassword) {
        return User.builder()
                .id(this.id)
                .password(newPassword)
                .role(this.role)
                .build();
    }
}
