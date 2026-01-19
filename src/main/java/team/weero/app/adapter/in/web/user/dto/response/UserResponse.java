package team.weero.app.adapter.in.web.user.dto.response;

import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public record UserResponse(UUID id, String email, Authority authority) {}
