package team.weero.app.adapter.in.web.auth.dto.request;

import team.weero.app.domain.auth.type.Authority;

public record SignUpCommand(
    String email,
    String password,
    String name,
    Authority authority,
    String accountId,
    String nickname,
    Integer grade,
    Integer classRoom,
    Integer number,
    String deviceToken) {}
