package team.weero.app.application.port.in.auth.dto.response;

import team.weero.app.domain.auth.type.Authority;

import java.time.LocalDateTime;

public record SignInInfo(
        String accessToken,
        String refreshToken,
        LocalDateTime accessTokenExpiredAt,
        LocalDateTime refreshTokenExpiredAt,
        Authority authority
) {
}
