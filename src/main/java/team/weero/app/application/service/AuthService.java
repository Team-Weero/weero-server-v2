package team.weero.app.application.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.application.port.in.GetCurrentUserUseCase;
import team.weero.app.application.port.in.ReissueTokenUseCase;
import team.weero.app.application.port.in.SignInCommand;
import team.weero.app.application.port.in.SignInResponse;
import team.weero.app.application.port.in.SignInUseCase;
import team.weero.app.application.port.in.SignUpCommand;
import team.weero.app.application.port.in.SignUpUseCase;
import team.weero.app.application.port.in.TokenResponse;
import team.weero.app.application.port.out.JwtPort;
import team.weero.app.application.port.out.LoadRefreshTokenPort;
import team.weero.app.application.port.out.LoadUserPort;
import team.weero.app.application.port.out.PasswordEncoderPort;
import team.weero.app.application.port.out.SaveRefreshTokenPort;
import team.weero.app.application.port.out.SaveStudentPort;
import team.weero.app.application.port.out.SaveUserPort;
import team.weero.app.domain.auth.AuthUser;
import team.weero.app.domain.auth.RefreshToken;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.domain.student.type.StudentRole;
import team.weero.app.global.exception.DuplicateEmailException;
import team.weero.app.global.exception.ExpiredTokenException;
import team.weero.app.global.exception.InvalidCredentialsException;
import team.weero.app.global.exception.InvalidTokenException;
import team.weero.app.global.exception.TeacherSignUpNotAllowedException;
import team.weero.app.global.exception.UserNotFoundException;
import team.weero.app.global.security.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class AuthService
    implements SignInUseCase, ReissueTokenUseCase, GetCurrentUserUseCase, SignUpUseCase {

  private final LoadUserPort loadUserPort;
  private final SaveUserPort saveUserPort;
  private final SaveStudentPort saveStudentPort;
  private final PasswordEncoderPort passwordEncoderPort;
  private final JwtPort jwtPort;
  private final SaveRefreshTokenPort saveRefreshTokenPort;
  private final LoadRefreshTokenPort loadRefreshTokenPort;

  @Override
  public SignInResponse execute(SignInCommand command) {
    AuthUser user =
        loadUserPort.loadByEmail(command.email()).orElseThrow(() -> UserNotFoundException.INSTANCE);

    String encodedPassword = loadUserPort.getEncodedPasswordByEmail(command.email());

    if (!passwordEncoderPort.matches(command.password(), encodedPassword)) {
      throw InvalidCredentialsException.INSTANCE;
    }

    String accessToken =
        jwtPort.generateAccessToken(user.getId(), user.getEmail(), user.getAuthority());
    String refreshTokenValue =
        jwtPort.generateRefreshToken(user.getId(), user.getEmail(), user.getAuthority());

    RefreshToken refreshToken =
        new RefreshToken(
            refreshTokenValue,
            user.getId(),
            user.getAuthority(),
            jwtPort.getRefreshTokenExpiredAt());

    saveRefreshTokenPort.save(refreshToken);

    LocalDateTime accessTokenExpiredAt = jwtPort.getAccessTokenExpiredAt();
    LocalDateTime refreshTokenExpiredAt = jwtPort.getRefreshTokenExpiredAt();

    return new SignInResponse(
        user.getId(),
        accessToken,
        refreshTokenValue,
        accessTokenExpiredAt,
        refreshTokenExpiredAt,
        user.getAuthority());
  }

  @Override
  public TokenResponse execute(String refreshToken) {
    RefreshToken token =
        loadRefreshTokenPort
            .loadByToken(refreshToken)
            .orElseThrow(() -> InvalidTokenException.INSTANCE);

    if (token.isExpired()) {
      throw ExpiredTokenException.INSTANCE;
    }

    String email = jwtPort.parseToken(refreshToken).email();

    AuthUser user =
        loadUserPort.loadByEmail(email).orElseThrow(() -> UserNotFoundException.INSTANCE);

    String newAccessToken =
        jwtPort.generateAccessToken(user.getId(), user.getEmail(), user.getAuthority());
    String newRefreshToken =
        jwtPort.generateRefreshToken(user.getId(), user.getEmail(), user.getAuthority());

    loadRefreshTokenPort.deleteByToken(refreshToken);

    RefreshToken newRefreshTokenDomain =
        new RefreshToken(
            newRefreshToken, user.getId(), user.getAuthority(), jwtPort.getRefreshTokenExpiredAt());

    saveRefreshTokenPort.save(newRefreshTokenDomain);

    LocalDateTime accessTokenExpiredAt = jwtPort.getAccessTokenExpiredAt();
    LocalDateTime refreshTokenExpiredAt = jwtPort.getRefreshTokenExpiredAt();

    return new TokenResponse(
        newAccessToken, newRefreshToken, accessTokenExpiredAt, refreshTokenExpiredAt);
  }

  @Override
  public AuthUser execute() {
    CustomUserDetails userDetails =
        (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return loadUserPort
        .loadByEmail(userDetails.getEmail())
        .orElseThrow(() -> UserNotFoundException.INSTANCE);
  }

  @Override
  @Transactional
  public void execute(SignUpCommand command) {
    if (command.authority() == Authority.TEACHER) {
      throw TeacherSignUpNotAllowedException.INSTANCE;
    }

    if (loadUserPort.loadByEmail(command.email()).isPresent()) {
      throw DuplicateEmailException.INSTANCE;
    }

    String encodedPassword = passwordEncoderPort.encode(command.password());

    UserJpaEntity user =
        UserJpaEntity.builder().email(command.email()).password(encodedPassword).build();

    UserJpaEntity savedUser = saveUserPort.save(user);

    StudentJpaEntity student =
        StudentJpaEntity.builder()
            .accountId(command.accountId())
            .name(command.name())
            .nickname(command.nickname())
            .grade(command.grade())
            .classRoom(command.classRoom())
            .number(command.number())
            .role(StudentRole.COMMON)
            .user(savedUser)
            .build();

    saveStudentPort.save(student);
  }
}
