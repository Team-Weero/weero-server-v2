package team.weero.app.application.port.out;

import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public record TokenClaims(UUID userId, String email, Authority authority) {}
