package team.weero.app.adapter.in.web.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GetAllPostResponse(List<PostItem> posts) {
  public record PostItem(
      UUID id,
      String title,
      String content,
      String nickName,
      int viewCount,
      LocalDateTime createdAt,
      LocalDateTime updatedAt
      /** 좋아요 조회수 추후 추가 */
      ) {}
}
