package team.weero.app.adapter.in.web.auth.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public record SignInResponse(
    UUID userId,
    String accessToken,
    String refreshToken,
    LocalDateTime accessTokenExpiredAt,
    LocalDateTime refreshTokenExpiredAt,
    Authority authority) {}
