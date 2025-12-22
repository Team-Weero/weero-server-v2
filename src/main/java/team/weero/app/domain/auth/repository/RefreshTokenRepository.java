package team.weero.app.domain.auth.repository;

import team.weero.app.domain.auth.model.RefreshToken;

import java.util.Optional;

/**
 * RefreshToken Repository Interface (Domain Layer)
 * Defines the contract for refresh token persistence operations
 */
public interface RefreshTokenRepository {

    /**
     * Save a refresh token
     * @param refreshToken RefreshToken to save
     * @return Saved RefreshToken
     */
    RefreshToken save(RefreshToken refreshToken);

    /**
     * Find a refresh token by account ID
     * @param accountId Account ID
     * @return Optional of RefreshToken
     */
    Optional<RefreshToken> findByAccountId(String accountId);

    /**
     * Find a refresh token by refresh token string
     * @param refreshToken Refresh token string
     * @return Optional of RefreshToken
     */
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    /**
     * Delete a refresh token by account ID
     * @param accountId Account ID
     */
    void deleteByAccountId(String accountId);
}
