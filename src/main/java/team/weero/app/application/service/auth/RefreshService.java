package team.weero.app.application.service.auth;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.application.port.in.auth.RefreshUseCase;
import team.weero.app.application.service.auth.dto.request.RefreshTokenRequest;
import team.weero.app.application.service.auth.dto.response.TokenResponse;
import team.weero.app.infrastructure.security.jwt.JwtProperties;
import team.weero.app.infrastructure.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class RefreshService implements RefreshUseCase {

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtProperties jwtProperties;

  public TokenResponse execute(RefreshTokenRequest request) {

    String newAccessToken = jwtTokenProvider.refreshAccessToken(request.refreshToken());

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
}
