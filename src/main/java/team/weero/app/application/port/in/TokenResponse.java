package team.weero.app.application.port.in;

import java.time.LocalDateTime;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    LocalDateTime accessTokenExpiredAt,
    LocalDateTime refreshTokenExpiredAt) {}
