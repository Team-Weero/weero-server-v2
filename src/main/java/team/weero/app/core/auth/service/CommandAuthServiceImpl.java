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
import team.weero.app.persistence.student.type.Role;
import team.weero.app.persistence.user.entity.User;

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
                        .accountId(request.accountId())
                        .password(passwordEncoder.encode(request.password()))
                        .build()
        );

        commandAuthPort.save(
                Student.builder()
                        .accountId(request.accountId())
                        .name(request.username())
                        .gcn(request.gcn())
                        .nickname("qwer") // 추후 구현 (닉네임 랜덤)
                        .studentRole(Role.COMMON)
                        .user(user)
                        .build()
        );
    }


    @Override
    public TokenResponse login(LoginRequest request) {
        User user = commandAuthPort.findByAccountId(request.accountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw PasswordIncorrectException.EXCEPTION;
        }

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(request.accountId()))
                .accessTokenExpiresAt(now.plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(request.accountId()))
                .refreshTokenExpiresAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                .build();
    }

}