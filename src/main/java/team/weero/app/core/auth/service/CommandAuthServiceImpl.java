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
import team.weero.app.infrastructure.jwt.JwtProperties;
import team.weero.app.infrastructure.jwt.JwtTokenProvider;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.type.StudentRole;
import team.weero.app.persistence.user.entity.User;
import team.weero.app.persistence.user.type.UserRole;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class CommandAuthServiceImpl implements CommandAuthService {

    private final CommandAuthPort commandAuthPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

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
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        
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
        
        // 학생으로 조회
        var studentOpt = commandAuthPort.findByAccountId(request.accountId());
        
        if (studentOpt.isPresent()) {
            var student = studentOpt.get();
            User user = student.getUser();
            
            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                throw PasswordIncorrectException.EXCEPTION;
            }
            
            return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(
                    student.getAccountId(), UserRole.STUDENT, student.getStudentRole()))
                .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(
                    student.getAccountId(), UserRole.STUDENT, student.getStudentRole()))
                .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                .deviceToken(null)
                .build();
        }
        
        // 둘 다 없으면 예외
        throw UserNotFoundException.EXCEPTION;
    }

    @Override
    public TokenResponse refresh(RefreshTokenRequest request) {
        // 1. 새로운 Access Token 발급
        String newAccessToken = jwtTokenProvider.refreshAccessToken(request.refreshToken());
        
        // 2. 새로운 Refresh Token 발급 (Refresh Token Rotation)
        String newRefreshToken = jwtTokenProvider.reissueRefreshToken(request.refreshToken());
        
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        
        return TokenResponse.builder()
            .accessToken(newAccessToken)
            .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
            .refreshToken(newRefreshToken)
            .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
            .deviceToken(null)
            .build();
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