package team.weero.app.domain.chat.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

public class MessageNotFoundException extends WeeRoException {
  public static final WeeRoException EXCEPTION = new MessageNotFoundException();

  private MessageNotFoundException() {
    super(ErrorCode.MESSAGE_NOT_FOUND);
  }
}
