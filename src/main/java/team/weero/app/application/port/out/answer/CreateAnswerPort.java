package team.weero.app.application.port.out.answer;

import team.weero.app.domain.answer.model.Answer;

public interface CreateAnswerPort {

  void save(Answer answer);
}
