package team.weero.app.adapter.out.auth;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.auth.entity.RefreshTokenJpaEntity;
import team.weero.app.adapter.out.auth.repository.RefreshTokenRedisRepository;
import team.weero.app.application.port.out.auth.LoadRefreshTokenPort;
import team.weero.app.application.port.out.auth.SaveRefreshTokenPort;
import team.weero.app.domain.auth.RefreshToken;

@Component
@RequiredArgsConstructor
public class RefreshTokenAdapter implements SaveRefreshTokenPort, LoadRefreshTokenPort {

  private final RefreshTokenRedisRepository refreshTokenRedisRepository;

  @Override
  public void save(RefreshToken refreshToken) {
    RefreshTokenJpaEntity entity = RefreshTokenJpaEntity.from(refreshToken);
    refreshTokenRedisRepository.save(entity);
  }

  @Override
  public Optional<RefreshToken> loadByToken(String token) {
    return refreshTokenRedisRepository.findByToken(token).map(RefreshTokenJpaEntity::toDomain);
  }

  @Override
  public void deleteByToken(String token) {
    refreshTokenRedisRepository.deleteByToken(token);
  }
}
