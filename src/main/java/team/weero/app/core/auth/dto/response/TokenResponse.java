package team.weero.app.core.auth.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record TokenResponse(String accessToken, ZonedDateTime accessTokenExpiresAt,
                            String refreshToken, ZonedDateTime refreshTokenExpiresAt,
                            String deviceToken) {
}
