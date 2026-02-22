package team.weero.app.adapter.in.web.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import team.weero.app.application.port.in.post.dto.response.PagedPostInfo;

@Schema(description = "게시글 페이지 응답")
public record PagedPostResponse(
    @Schema(description = "게시글 목록") List<PostItem> posts,
    @Schema(description = "현재 페이지 (0부터 시작)") int page,
    @Schema(description = "페이지 크기") int size,
    @Schema(description = "전체 게시글 수") long totalElements,
    @Schema(description = "전체 페이지 수") int totalPages,
    @Schema(description = "다음 페이지 존재 여부") boolean hasNext) {

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

  public static PagedPostResponse from(PagedPostInfo info) {
    List<PostItem> items =
        info.posts().stream()
            .map(
                p ->
                    new PostItem(
                        p.id(),
                        p.title(),
                        p.nickName(),
                        p.viewCount(),
                        p.heartCount(),
                        p.hearted(),
                        p.createdAt(),
                        p.updatedAt()))
            .toList();

    return new PagedPostResponse(
        items, info.page(), info.size(), info.totalElements(), info.totalPages(), info.hasNext());
  }
}
