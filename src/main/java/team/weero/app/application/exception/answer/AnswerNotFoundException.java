package team.weero.app.application.exception.answer;

import team.weero.app.application.exception.answer.error.AnswerErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class AnswerNotFoundException extends WeeRoException {

  public static final AnswerNotFoundException INSTANCE = new AnswerNotFoundException();

  private AnswerNotFoundException() {
    super(AnswerErrorCode.ANSWER_NOT_FOUND);
  }
}
