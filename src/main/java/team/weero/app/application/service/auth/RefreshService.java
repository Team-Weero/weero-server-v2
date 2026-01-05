package team.weero.app.application.auth.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.application.auth.dto.request.RefreshTokenRequest;
import team.weero.app.application.auth.dto.response.TokenResponse;
import team.weero.app.infrastructure.security.jwt.JwtProperties;
import team.weero.app.infrastructure.security.jwt.JwtTokenProvider;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Refresh Token Use Case
 * Application layer use case for refreshing access tokens
 */
@Service
@RequiredArgsConstructor
public class RefreshUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    public TokenResponse execute(RefreshTokenRequest request) {
        // Generate new access token
        String newAccessToken = jwtTokenProvider.refreshAccessToken(request.refreshToken());

        // Generate new refresh token (Refresh Token Rotation)
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
