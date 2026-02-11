package team.weero.app.application.port.in.auth.dto.response;

import java.time.LocalDateTime;

public record TokenInfo(
        String accessToken,
        String refreshToken,
        LocalDateTime accessTokenExpiredAt,
        LocalDateTime refreshTokenExpiredAt
) {
}
