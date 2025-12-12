package team.weero.app.core.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.weero.app.core.auth.dto.request.LoginRequest;
import team.weero.app.core.auth.dto.request.RefreshTokenRequest;
import team.weero.app.core.auth.dto.request.SignupRequest;
import team.weero.app.core.auth.dto.response.TokenResponse;
import team.weero.app.core.auth.exception.PasswordIncorrectException;
import team.weero.app.core.auth.exception.UserAlreadyExistsException;
import team.weero.app.core.auth.exception.UserNotFoundException;
import team.weero.app.core.auth.spi.CommandAuthPort;
import team.weero.app.core.auth.spi.TokenPort;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.type.StudentRole;
import team.weero.app.persistence.user.entity.User;
import team.weero.app.persistence.user.type.UserRole;

@Service
@RequiredArgsConstructor
public class CommandAuthServiceImpl implements CommandAuthService {

    private final CommandAuthPort commandAuthPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenPort tokenPort;

    @Override
    public void signup(SignupRequest request) {
        if (commandAuthPort.findByAccountId(request.accountId()).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        User user = commandAuthPort.save(
            User.builder()
                .userRole(UserRole.STUDENT)
                .password(passwordEncoder.encode(request.password()))
                .build()
        );

        commandAuthPort.save(
            Student.builder()
                .accountId(request.accountId())
                .name(request.username())
                .gcn(request.gcn())
                .nickname(generateRandomNickname()) // 랜덤 닉네임 생성
                .studentRole(StudentRole.COMMON)
                .user(user)
                .build()
        );
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        // 선생님으로 조회
        var teacherOpt = commandAuthPort.findTeacherByAccountId(request.accountId());
        if (teacherOpt.isPresent()) {
            var teacher = teacherOpt.get();
            User user = teacher.getUser();

            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                throw PasswordIncorrectException.EXCEPTION;
            }

            // deviceToken 업데이트
            String deviceToken = request.deviceToken();
            if (deviceToken != null && !deviceToken.isBlank()) {
                teacher.updateDeviceToken(deviceToken);
                commandAuthPort.save(teacher);
            }

            TokenResponse tokenResponse = tokenPort.generateTokenResponse(teacher.getAccountId(), UserRole.TEACHER, null);
            return tokenResponse.toBuilder().deviceToken(deviceToken).build();
        }

        // 학생으로 조회
        var studentOpt = commandAuthPort.findByAccountId(request.accountId());
        if (studentOpt.isPresent()) {
            var student = studentOpt.get();
            User user = student.getUser();

            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                throw PasswordIncorrectException.EXCEPTION;
            }

            return tokenPort.generateTokenResponse(student.getAccountId(), UserRole.STUDENT, student.getStudentRole());
        }

        // 둘 다 없으면 예외
        throw UserNotFoundException.EXCEPTION;
    }

    @Override
    public TokenResponse refresh(RefreshTokenRequest request) {
        return tokenPort.refreshToken(request.refreshToken());
    }

    /**
     * 랜덤 닉네임 생성 메서드
     * TODO: 실제 랜덤 닉네임 로직 구현
     */
    private String generateRandomNickname() {
        // 임시 구현 - 실제로는 랜덤 닉네임 생성 로직 추가
        return "user_" + System.currentTimeMillis();
    }
}