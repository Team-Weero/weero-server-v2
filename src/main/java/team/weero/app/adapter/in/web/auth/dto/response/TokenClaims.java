package team.weero.app.adapter.in.web.auth.dto.response;

import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public record TokenClaims(UUID userId, String email, Authority authority) {}
