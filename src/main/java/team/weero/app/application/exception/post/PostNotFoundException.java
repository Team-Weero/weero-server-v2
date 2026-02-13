package team.weero.app.application.exception.post;

import team.weero.app.application.exception.post.error.PostErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;

public class PostNotFoundException extends WeeRoException {
  public static final PostNotFoundException INSTANCE = new PostNotFoundException();

  private PostNotFoundException() {
    super(PostErrorCode.POST_NOT_FOUND);
  }
}
