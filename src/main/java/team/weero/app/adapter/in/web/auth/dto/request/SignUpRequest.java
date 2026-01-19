package team.weero.app.adapter.in.web.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import team.weero.app.domain.auth.type.Authority;

public record SignUpRequest(
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotBlank String name,
    @NotNull Authority authority,
    String accountId,
    String nickname,
    Integer grade,
    Integer classRoom,
    Integer number,
    String deviceToken) {}
