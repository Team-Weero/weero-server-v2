package team.weero.app.persistence.auth.repository;

import org.springframework.data.repository.CrudRepository;
import team.weero.app.persistence.auth.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
