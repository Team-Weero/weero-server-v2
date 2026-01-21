package team.weero.app.application.port.out.answer;

import java.util.UUID;

public interface CreateAnswerPort {

  void save(String answer, UUID userId, UUID postId);
}
