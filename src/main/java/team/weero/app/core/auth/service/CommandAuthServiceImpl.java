package team.weero.app.core.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.weero.app.core.auth.dto.request.LoginRequest;
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
        if (commandAuthPort.findByStudentAccountId(request.accountId()).isPresent()) {
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
                        .nickname("qwer") // 랜덤 닉네임 구현 가능
                        .studentRole(StudentRole.COMMON)
                        .user(user)
                        .build()
        );
    }

    @Override
    public TokenResponse login(LoginRequest request) {

        var teacherOpt = commandAuthPort.findByTeacherAccountId(request.accountId());

        var studentOpt = commandAuthPort.findByStudentAccountId(request.accountId());

        User user;
        UserRole userRole;
        StudentRole studentRole = null;
        String accountId;

        if (teacherOpt.isPresent()) {
            var teacher = teacherOpt.get();
            user = teacher.getUser();
            userRole = UserRole.TEACHER;
            accountId = teacher.getAccountId();
        } else if (studentOpt.isPresent()) {
            var student = studentOpt.get();
            user = student.getUser();
            userRole = UserRole.STUDENT;
            studentRole = student.getStudentRole();
            accountId = student.getAccountId();
        } else {
            throw UserNotFoundException.EXCEPTION;
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw PasswordIncorrectException.EXCEPTION;
        }

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(accountId, userRole, studentRole))
                .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(accountId, userRole, studentRole))
                .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                .build();
    }
}
