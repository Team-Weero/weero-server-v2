package team.weero.app.adapter.out.persistence.auth;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.auth.mapper.RefreshTokenMapper;
import team.weero.app.adapter.out.persistence.auth.repository.RefreshTokenRedisRepository;
import team.weero.app.application.port.out.auth.RefreshTokenPort;
import team.weero.app.domain.auth.model.RefreshToken;

@Component
@RequiredArgsConstructor
public class RefreshTokenPersistenceAdapter implements RefreshTokenPort {

  private final RefreshTokenRedisRepository refreshTokenRedisRepository;

  @Override
  public RefreshToken save(RefreshToken refreshToken) {
    var entity = RefreshTokenMapper.toEntity(refreshToken);
    var savedEntity = refreshTokenRedisRepository.save(entity);
    return RefreshTokenMapper.toDomain(savedEntity);
  }

  @Override
  public Optional<RefreshToken> findByAccountId(String accountId) {
    return refreshTokenRedisRepository.findById(accountId).map(RefreshTokenMapper::toDomain);
  }

  @Override
  public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
    return refreshTokenRedisRepository
        .findByRefreshToken(refreshToken)
        .map(RefreshTokenMapper::toDomain);
  }

  @Override
  public void deleteByAccountId(String accountId) {
    refreshTokenRedisRepository.deleteById(accountId);
  }
}
