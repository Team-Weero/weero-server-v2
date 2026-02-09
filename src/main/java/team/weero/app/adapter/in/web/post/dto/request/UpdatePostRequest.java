package team.weero.app.adapter.in.web.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "게시글 수정 요청")
public record UpdatePostRequest(
    @Schema(description = "게시글 제목 (최대 100자)", example = "수정된 게시글 제목입니다") @Size(max = 100)
        String title,
    @Schema(description = "게시글 내용 (최대 10,000자)", example = "수정된 게시글 내용입니다") @Size(max = 10_000)
        String content) {}
