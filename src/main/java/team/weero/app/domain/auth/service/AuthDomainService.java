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

@Service
@RequiredArgsConstructor
public class AuthDomainService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerStudent(String accountId, String username, String gcn, String password) {
        if (authRepository.findStudentByAccountId(accountId).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .password(passwordEncoder.encode(password))
                .role(UserRole.STUDENT)
                .build();

        User savedUser = authRepository.saveUser(user);

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

    private String generateRandomNickname() {
        return "user_" + System.currentTimeMillis();
    }
}
