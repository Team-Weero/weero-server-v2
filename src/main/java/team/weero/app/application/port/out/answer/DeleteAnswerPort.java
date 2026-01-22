package team.weero.app.application.port.out.answer;

import java.util.UUID;

public interface DeleteAnswerPort {

  void softDelete(UUID answerId);
}
