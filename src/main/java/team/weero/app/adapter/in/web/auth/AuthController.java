package team.weero.app.adapter.in.web.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weero.app.adapter.in.web.auth.dto.request.SignInCommand;
import team.weero.app.adapter.in.web.auth.dto.request.SignInRequest;
import team.weero.app.adapter.in.web.auth.dto.request.SignUpCommand;
import team.weero.app.adapter.in.web.auth.dto.request.SignUpRequest;
import team.weero.app.adapter.in.web.auth.dto.response.SignInResponse;
import team.weero.app.adapter.in.web.auth.dto.response.TokenResponse;
import team.weero.app.adapter.in.web.user.dto.response.UserResponse;
import team.weero.app.application.port.in.auth.ReissueTokenUseCase;
import team.weero.app.application.port.in.auth.SignInUseCase;
import team.weero.app.application.port.in.auth.SignUpUseCase;
import team.weero.app.application.port.in.notice.GetCurrentUserUseCase;
import team.weero.app.domain.auth.AuthUser;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final SignInUseCase signInUseCase;
  private final SignUpUseCase signUpUseCase;
  private final ReissueTokenUseCase reissueTokenUseCase;
  private final GetCurrentUserUseCase getCurrentUserUseCase;

  @PostMapping("/signin")
  public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest request) {
    SignInCommand command = new SignInCommand(request.email(), request.password());
    SignInResponse response = signInUseCase.execute(command);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/reissue")
  public ResponseEntity<TokenResponse> reissueToken(
      @RequestHeader("Authorization") String authorization) {
    String token = authorization.substring("Bearer ".length());
    TokenResponse response = reissueTokenUseCase.execute(token);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getCurrentUser() {
    AuthUser authUser = getCurrentUserUseCase.execute();
    UserResponse response =
        new UserResponse(authUser.getId(), authUser.getEmail(), authUser.getAuthority());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/signup")
  public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest request) {
    SignUpCommand command =
        new SignUpCommand(
            request.email(),
            request.password(),
            request.name(),
            request.authority(),
            request.accountId(),
            request.nickname(),
            request.grade(),
            request.classRoom(),
            request.number(),
            request.deviceToken());
    signUpUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
