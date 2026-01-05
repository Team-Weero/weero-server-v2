package team.weero.app.application.port.in.auth;

import team.weero.app.application.service.auth.dto.request.SignupRequest;

public interface SignupUseCase {
    void execute(SignupRequest request);
}
