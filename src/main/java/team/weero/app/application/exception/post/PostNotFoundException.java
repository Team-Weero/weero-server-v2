package team.weero.app.application.exception.post;

import team.weero.app.application.exception.post.error.PostErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

public class PostNotFoundException extends WeeRoException {
  public PostNotFoundException() {
    super(PostErrorCode.POST_NOT_FOUND);
  }
}
