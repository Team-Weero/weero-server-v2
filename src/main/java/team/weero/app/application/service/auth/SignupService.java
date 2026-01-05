package team.weero.app.application.auth.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.application.auth.dto.request.SignupRequest;
import team.weero.app.domain.auth.service.AuthDomainService;

/**
 * Signup Use Case
 * Application layer use case for user registration
 */
@Service
@RequiredArgsConstructor
public class SignupUseCase {

    private final AuthDomainService authDomainService;

    public void execute(SignupRequest request) {
        authDomainService.registerStudent(
                request.accountId(),
                request.username(),
                request.gcn(),
                request.password()
        );
    }
}
