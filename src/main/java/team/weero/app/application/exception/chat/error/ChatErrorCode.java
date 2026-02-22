package team.weero.app.application.exception.chat.error;

import team.weero.app.global.error.dto.ErrorProperty;

public enum ChatErrorCode implements ErrorProperty {
  CHAT_ROOM_NOT_FOUND(404, "Chat Room Not Found", 1),
  FORBIDDEN_CHAT_ROOM_ACCESS(403, "Forbidden Chatroom Access", 1),
  ;

  private final int status;
  private final String message;
  private final int sequence;

  ChatErrorCode(int status, String message, int sequence) {
    this.status = status;
    this.message = message;
    this.sequence = sequence;
  }

  @Override
  public int status() {
    return status;
  }

  @Override
  public String message() {
    return message;
  }

  @Override
  public String code() {
    return "CHAT-" + status + "-" + sequence;
  }
}
