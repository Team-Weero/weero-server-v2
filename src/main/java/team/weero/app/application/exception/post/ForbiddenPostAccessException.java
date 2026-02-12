package team.weero.app.application.exception.post;

import team.weero.app.application.exception.post.error.PostErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class ForbiddenPostAccessException extends WeeRoException {
  public static final ForbiddenPostAccessException INSTANCE = new ForbiddenPostAccessException();

  private ForbiddenPostAccessException() {
    super(PostErrorCode.FORBIDDEN_POST_ACCESS);
  }
}
