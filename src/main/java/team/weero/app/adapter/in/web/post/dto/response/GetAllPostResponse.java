package team.weero.app.adapter.in.web.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Schema(description = "게시글 목록 조회 응답")
public record GetAllPostResponse(
    @Schema(description = "게시글 목록") List<PostItem> posts) {

  @Schema(description = "게시글 항목")
  public record PostItem(
      @Schema(description = "게시글 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID id,
      @Schema(description = "게시글 제목", example = "게시글 제목입니다") String title,
      @Schema(description = "게시글 내용", example = "게시글 내용입니다") String content,
      @Schema(description = "작성자 닉네임", example = "홍길동") String nickName,
      @Schema(description = "생성 일시", example = "2024-01-01T10:00:00") LocalDateTime createdAt,
      @Schema(description = "수정 일시", example = "2024-01-01T10:00:00") LocalDateTime updatedAt
      /** 좋아요 조회수 추후 추가 */
      ) {}
}
