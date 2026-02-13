package team.weero.app.application.port.in.auth.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public record SignInInfo(
    UUID userId,
    String accessToken,
    String refreshToken,
    LocalDateTime accessTokenExpiredAt,
    LocalDateTime refreshTokenExpiredAt,
    Authority authority) {}
