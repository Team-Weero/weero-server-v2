package team.weero.app.application.service.auth.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record TokenResponse(String accessToken, ZonedDateTime accessTokenExpiresAt,
                            String refreshToken, ZonedDateTime refreshTokenExpiresAt,
                            String deviceToken) {
}
