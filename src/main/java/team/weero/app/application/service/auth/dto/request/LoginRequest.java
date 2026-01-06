package team.weero.app.application.service.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "accountId는 필수입니다.") String accountId,
    @NotBlank(message = "비밀번호는 필수입니다.") @Size(min = 8, max = 255, message = "비밀번호는 8자 이상 입력해야 합니다.")
        String password,
    String deviceToken) {}
