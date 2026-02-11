package team.weero.app.application.port.in.user.dto.response;

import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public record UserInfo(UUID id, String email, Authority authority) {}
