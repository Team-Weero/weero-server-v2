package team.weero.app.application.auth.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.weero.app.application.auth.dto.request.LoginRequest;
import team.weero.app.application.auth.dto.response.TokenResponse;
import team.weero.app.domain.auth.exception.PasswordIncorrectException;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.domain.auth.repository.AuthRepository;
import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.user.model.UserRole;
import team.weero.app.infrastructure.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.infrastructure.persistence.user.entity.UserJpaEntity;
import team.weero.app.infrastructure.security.jwt.JwtProperties;
import team.weero.app.infrastructure.security.jwt.JwtTokenProvider;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Login Use Case
 * Application layer use case for user authentication
 */
@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    public TokenResponse execute(LoginRequest request) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        // Try to find teacher first
        var teacherOpt = authRepository.findTeacherByAccountId(request.accountId());

        if (teacherOpt.isPresent()) {
            TeacherJpaEntity teacher = teacherOpt.get();
            UserJpaEntity user = teacher.getUser();

            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                throw PasswordIncorrectException.EXCEPTION;
            }

            // Update device token if provided
            String deviceToken = request.deviceToken();
            if (deviceToken != null && !deviceToken.isBlank()) {
                teacher.updateDeviceToken(deviceToken);
                authRepository.saveTeacher(teacher);
            }

            return TokenResponse.builder()
                    .accessToken(jwtTokenProvider.generateAccessToken(
                            teacher.getAccountId(), UserRole.TEACHER, null))
                    .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
                    .refreshToken(jwtTokenProvider.generateRefreshToken(
                            teacher.getAccountId(), UserRole.TEACHER, null))
                    .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                    .deviceToken(deviceToken)
                    .build();
        }

        // Try to find student
        var studentOpt = authRepository.findStudentByAccountId(request.accountId());

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();

            // Get student with user for password verification (temporary until full migration)
            var studentWithUserOpt = authRepository.findStudentWithUserByAccountId(request.accountId());
            if (studentWithUserOpt.isEmpty()) {
                throw UserNotFoundException.EXCEPTION;
            }

            var studentWithUser = studentWithUserOpt.get();
            if (!passwordEncoder.matches(request.password(), studentWithUser.getUser().getPassword())) {
                throw PasswordIncorrectException.EXCEPTION;
            }

            return TokenResponse.builder()
                    .accessToken(jwtTokenProvider.generateAccessToken(
                            student.getAccountId(), UserRole.STUDENT, student.getRole()))
                    .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
                    .refreshToken(jwtTokenProvider.generateRefreshToken(
                            student.getAccountId(), UserRole.STUDENT, student.getRole()))
                    .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                    .deviceToken(null)
                    .build();
        }

        throw UserNotFoundException.EXCEPTION;
    }
}
