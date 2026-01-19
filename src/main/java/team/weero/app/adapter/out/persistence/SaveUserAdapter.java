package team.weero.app.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.user.repository.UserRepository;
import team.weero.app.application.port.out.user.SaveUserPort;

@Component
@RequiredArgsConstructor
public class SaveUserAdapter implements SaveUserPort {

  private final UserRepository userRepository;

  @Override
  public UserJpaEntity save(UserJpaEntity user) {
    return userRepository.save(user);
  }
}
