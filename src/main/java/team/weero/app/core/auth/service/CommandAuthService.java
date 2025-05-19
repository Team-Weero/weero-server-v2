package team.weero.app.core.auth.service;

import org.springframework.stereotype.Service;
import team.weero.app.core.auth.dto.request.LoginRequest;
import team.weero.app.core.auth.dto.request.SignupRequest;
import team.weero.app.core.auth.dto.response.TokenResponse;

@Service
public interface CommandAuthService {

    public void signup (SignupRequest request);

    public TokenResponse login (LoginRequest request);
}
