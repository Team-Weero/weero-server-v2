package team.weero.app.application.exception.post;

import team.weero.app.application.exception.post.error.PostErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class ForbiddenPostAccessException extends WeeRoException {
  public ForbiddenPostAccessException() {
    super(PostErrorCode.FORBIDDEN_POST_ACCESS);
  }
}
