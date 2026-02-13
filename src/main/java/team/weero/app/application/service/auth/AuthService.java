package team.weero.app.application.service.auth;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.application.exception.auth.DuplicateEmailException;
import team.weero.app.application.exception.auth.TeacherSignUpNotAllowedException;
import team.weero.app.application.exception.user.UserNotFoundException;
import team.weero.app.application.port.in.auth.ReissueTokenUseCase;
import team.weero.app.application.port.in.auth.SignInUseCase;
import team.weero.app.application.port.in.auth.SignUpUseCase;
import team.weero.app.application.port.in.auth.dto.request.SignInCommand;
import team.weero.app.application.port.in.auth.dto.request.SignUpCommand;
import team.weero.app.application.port.in.auth.dto.response.SignInInfo;
import team.weero.app.application.port.in.auth.dto.response.TokenInfo;
import team.weero.app.application.port.in.user.GetCurrentUserUseCase;
import team.weero.app.application.port.in.user.dto.response.UserInfo;
import team.weero.app.application.port.out.auth.JwtPort;
import team.weero.app.application.port.out.auth.LoadRefreshTokenPort;
import team.weero.app.application.port.out.auth.PasswordEncoderPort;
import team.weero.app.application.port.out.auth.SaveRefreshTokenPort;
import team.weero.app.application.port.out.student.SaveStudentPort;
import team.weero.app.application.port.out.user.LoadUserPort;
import team.weero.app.application.port.out.user.SaveUserPort;
import team.weero.app.domain.auth.RefreshToken;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.domain.student.type.StudentRole;
import team.weero.app.global.security.jwt.exception.ExpiredTokenException;
import team.weero.app.global.security.jwt.exception.InvalidCredentialsException;
import team.weero.app.global.security.jwt.exception.InvalidTokenException;
import team.weero.app.global.security.principal.CustomUserDetails;

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
  public SignInInfo execute(SignInCommand command) {
    UserInfo user =
        loadUserPort.loadByEmail(command.email()).orElseThrow(() -> UserNotFoundException.INSTANCE);

    String encodedPassword = loadUserPort.getEncodedPasswordByEmail(command.email());

    if (!passwordEncoderPort.matches(command.password(), encodedPassword)) {
      throw InvalidCredentialsException.INSTANCE;
    }

    String accessToken = jwtPort.generateAccessToken(user.id(), user.email(), user.authority());
    String refreshTokenValue =
        jwtPort.generateRefreshToken(user.id(), user.email(), user.authority());

    RefreshToken refreshToken =
        new RefreshToken(
            refreshTokenValue, user.id(), user.authority(), jwtPort.getRefreshTokenExpiredAt());

    saveRefreshTokenPort.save(refreshToken);

    LocalDateTime accessTokenExpiredAt = jwtPort.getAccessTokenExpiredAt();
    LocalDateTime refreshTokenExpiredAt = jwtPort.getRefreshTokenExpiredAt();

    return new SignInInfo(
        user.id(),
        accessToken,
        refreshTokenValue,
        accessTokenExpiredAt,
        refreshTokenExpiredAt,
        user.authority());
  }

  @Override
  public TokenInfo execute(String refreshToken) {
    RefreshToken token =
        loadRefreshTokenPort
            .loadByToken(refreshToken)
            .orElseThrow(() -> InvalidTokenException.INSTANCE);

    if (token.isExpired()) {
      throw ExpiredTokenException.INSTANCE;
    }

    String email = jwtPort.parseToken(refreshToken).email();

    UserInfo user =
        loadUserPort.loadByEmail(email).orElseThrow(() -> UserNotFoundException.INSTANCE);

    String newAccessToken = jwtPort.generateAccessToken(user.id(), user.email(), user.authority());
    String newRefreshToken =
        jwtPort.generateRefreshToken(user.id(), user.email(), user.authority());

    loadRefreshTokenPort.deleteByToken(refreshToken);

    RefreshToken newRefreshTokenDomain =
        new RefreshToken(
            newRefreshToken, user.id(), user.authority(), jwtPort.getRefreshTokenExpiredAt());

    saveRefreshTokenPort.save(newRefreshTokenDomain);

    LocalDateTime accessTokenExpiredAt = jwtPort.getAccessTokenExpiredAt();
    LocalDateTime refreshTokenExpiredAt = jwtPort.getRefreshTokenExpiredAt();

    return new TokenInfo(
        newAccessToken, newRefreshToken, accessTokenExpiredAt, refreshTokenExpiredAt);
  }

  @Override
  public UserInfo execute() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (!(principal instanceof CustomUserDetails)) {
      throw new IllegalStateException("Authentication principal is not CustomUserDetails");
    }

    CustomUserDetails userDetails = (CustomUserDetails) principal;

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
        UserJpaEntity.builder()
            .email(command.email())
            .password(encodedPassword)
            .authority(command.authority())
            .build();

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
