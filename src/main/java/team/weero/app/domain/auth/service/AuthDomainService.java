package team.weero.app.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.weero.app.domain.auth.exception.UserAlreadyExistsException;
import team.weero.app.application.port.out.auth.AuthRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.student.model.StudentRole;
import team.weero.app.domain.user.model.User;
import team.weero.app.domain.user.model.UserRole;

import java.util.UUID;

/**
 * Auth Domain Service
 * Handles complex business logic that spans multiple aggregates
 */
@Service
@RequiredArgsConstructor
public class AuthDomainService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Register a new student (User + Student creation)
     * This is a domain service because it coordinates multiple aggregates
     */
    public void registerStudent(String accountId, String username, String gcn, String password) {
        // Check if user already exists
        if (authRepository.findStudentByAccountId(accountId).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        // Create and save User
        User user = User.builder()
                .id(UUID.randomUUID())
                .password(passwordEncoder.encode(password))
                .role(UserRole.STUDENT)
                .build();

        User savedUser = authRepository.saveUser(user);

        // Create and save Student
        Student student = Student.builder()
                .id(UUID.randomUUID())
                .accountId(accountId)
                .name(username)
                .gcn(gcn)
                .nickname(generateRandomNickname())
                .role(StudentRole.COMMON)
                .userId(savedUser.getId())
                .build();

        authRepository.saveStudent(student);
    }

    /**
     * Generate random nickname
     * TODO: Implement proper random nickname generation logic
     */
    private String generateRandomNickname() {
        return "user_" + System.currentTimeMillis();
    }
}
