package team.weero.app.core.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(

        @NotBlank(message = "사용자 이름은 필수입니다.")
        @Size(min = 1, max = 20, message = "사용자 이름은 1자 이상 20자 이하로 입력해야 합니다.")
        String username,

        @NotBlank(message = "학번은 필수입니다.")
        @Size(max = 4)
        String gcn,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 255, message = "비밀번호는 8자 이상 입력해야 합니다.")
        String password,

        @NotBlank(message = "accountId는 필수입니다.")
        String accountId

) {}
