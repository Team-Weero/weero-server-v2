package team.weero.app.application.port.out;

import team.weero.app.adapter.out.user.entity.UserJpaEntity;

public interface SaveUserPort {
  UserJpaEntity save(UserJpaEntity user);
}
