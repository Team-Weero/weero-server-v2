package team.weero.app.adapter.in.web.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weero.app.adapter.in.web.auth.dto.request.SignInRequest;
import team.weero.app.adapter.in.web.auth.dto.request.SignUpRequest;
import team.weero.app.adapter.in.web.auth.dto.response.SignInResponse;
import team.weero.app.adapter.in.web.auth.dto.response.TokenResponse;
import team.weero.app.adapter.in.web.user.dto.response.UserResponse;
import team.weero.app.application.port.in.auth.ReissueTokenUseCase;
import team.weero.app.application.port.in.auth.SignInUseCase;
import team.weero.app.application.port.in.auth.SignUpUseCase;
import team.weero.app.application.port.in.auth.dto.request.SignInCommand;
import team.weero.app.application.port.in.notice.GetCurrentUserUseCase;

@Tag(name = "Authentication", description = "인증 및 회원 관리 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final SignInUseCase signInUseCase;
  private final SignUpUseCase signUpUseCase;
  private final ReissueTokenUseCase reissueTokenUseCase;
  private final GetCurrentUserUseCase getCurrentUserUseCase;

  @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하여 JWT 토큰을 발급받습니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "로그인 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "401", description = "인증 실패")
  })
  @PostMapping("/signin")
  public SignInResponse signIn(@RequestBody @Valid SignInRequest request) {
    return SignInResponse.from(
        signInUseCase.execute(new SignInCommand(request.email(), request.password())));
  }

  @Operation(summary = "토큰 재발급", description = "Refresh Token을 사용하여 새로운 Access Token을 발급받습니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "재발급 성공"),
    @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰")
  })
  @PostMapping("/reissue")
  public TokenResponse reissueToken(@RequestHeader("Authorization") String authorization) {
    String token = authorization.substring("Bearer ".length());
    return TokenResponse.from(reissueTokenUseCase.execute(token));
  }

  @Operation(summary = "현재 로그인한 사용자 정보 조회", description = "JWT 토큰을 통해 현재 로그인한 사용자의 정보를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패")
  })
  @SecurityRequirement(name = "bearer-key")
  @GetMapping("/me")
  public UserResponse getCurrentUser() {
    return UserResponse.from(getCurrentUserUseCase.execute());
  }

  @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "회원가입 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "409", description = "이미 존재하는 이메일")
  })
  @PostMapping("/signup")
  public void signUp(@RequestBody @Valid SignUpRequest request) {
    signUpUseCase.execute(SignUpRequest.from(request));
  }
}
