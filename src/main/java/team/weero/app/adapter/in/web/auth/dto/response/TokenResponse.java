package team.weero.app.adapter.in.web.auth.dto.response;

import java.time.LocalDateTime;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    LocalDateTime accessTokenExpiredAt,
    LocalDateTime refreshTokenExpiredAt) {}
