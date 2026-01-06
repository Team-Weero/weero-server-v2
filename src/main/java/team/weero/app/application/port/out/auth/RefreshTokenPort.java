package team.weero.app.application.port.out.auth;

import team.weero.app.domain.auth.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenPort {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByAccountId(String accountId);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    void deleteByAccountId(String accountId);
}
