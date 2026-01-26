package team.weero.app.application.exception.answer;

import team.weero.app.application.exception.answer.error.AnswerErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class AnswerAccessDeniedException extends WeeRoException {

  public static final AnswerAccessDeniedException INSTANCE = new AnswerAccessDeniedException();

  private AnswerAccessDeniedException() {
    super(AnswerErrorCode.ANSWER_ACCESS_DENIED);
  }
}
