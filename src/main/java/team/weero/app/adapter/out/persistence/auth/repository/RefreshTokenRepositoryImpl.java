package team.weero.app.adapter.out.persistence.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.weero.app.domain.auth.model.RefreshToken;
import team.weero.app.application.port.out.auth.RefreshTokenRepository;
import team.weero.app.adapter.out.persistence.auth.mapper.RefreshTokenMapper;

import java.util.Optional;


 * RefreshToken Repository Implementation
 * Infrastructure layer implementation of RefreshTokenRepository
 */
@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        var entity = RefreshTokenMapper.toEntity(refreshToken);
        var savedEntity = refreshTokenRedisRepository.save(entity);
        return RefreshTokenMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<RefreshToken> findByAccountId(String accountId) {
        return refreshTokenRedisRepository.findById(accountId)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        return refreshTokenRedisRepository.findByRefreshToken(refreshToken)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public void deleteByAccountId(String accountId) {
        refreshTokenRedisRepository.deleteById(accountId);
    }
}
