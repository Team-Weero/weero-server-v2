package team.weero.app.domain.chat.exception;

import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.WeeRoException;

public class UnauthorizedChatAccessException extends WeeRoException {
  public static final WeeRoException EXCEPTION = new UnauthorizedChatAccessException();

  private UnauthorizedChatAccessException() {
    super(ErrorCode.UNAUTHORIZED_CHAT_ACCESS);
  }
}
