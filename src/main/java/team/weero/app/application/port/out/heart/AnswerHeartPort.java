package team.weero.app.application.port.out.heart;

import java.util.UUID;

public interface AnswerHeartPort {
  boolean exists(UUID answerId, UUID userId);

  void save(UUID answerId, UUID userId);

  void delete(UUID answerId, UUID userId);

  int countByAnswerId(UUID answerId);
}
