package team.weero.app.application.service.auth.dto.response;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record TokenResponse(
    String accessToken,
    ZonedDateTime accessTokenExpiresAt,
    String refreshToken,
    ZonedDateTime refreshTokenExpiresAt,
    String deviceToken) {}
