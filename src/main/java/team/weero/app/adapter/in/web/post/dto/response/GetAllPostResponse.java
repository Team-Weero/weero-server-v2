package team.weero.app.adapter.in.web.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import team.weero.app.application.port.in.post.dto.response.GetAllPostInfo;

@Schema(description = "게시글 목록 조회 응답")
public record GetAllPostResponse(@Schema(description = "게시글 목록") List<PostItem> posts) {

  @Schema(description = "게시글 항목")
  public record PostItem(
      UUID id,
      String title,
      String nickName,
      int viewCount,
      int heartCount,
      boolean hearted,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {}

  public static GetAllPostResponse from(GetAllPostInfo info) {
    List<PostItem> items =
        info.posts().stream()
            .map(
                postInfo ->
                    new PostItem(
                        postInfo.id(),
                        postInfo.title(),
                        postInfo.nickName(),
                        postInfo.viewCount(),
                        postInfo.heartCount(),
                        postInfo.hearted(),
                        postInfo.createdAt(),
                        postInfo.updatedAt()))
            .collect(Collectors.toList());

    return new GetAllPostResponse(items);
  }
}
