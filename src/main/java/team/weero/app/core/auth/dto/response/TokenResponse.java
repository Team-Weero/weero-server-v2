package team.weero.app.core.auth.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder(toBuilder = true)
public record TokenResponse(String accessToken, ZonedDateTime accessTokenExpiresAt,
                            String refreshToken, ZonedDateTime refreshTokenExpiresAt,
                            String deviceToken) {
}
