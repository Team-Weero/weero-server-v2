package team.weero.app.adapter.in.web.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "게시글 조회 응답")
public record GetPostResponse(
    UUID id,
    String title,
    String content,
    String nickName,
    int viewCount,
    int heartCount,
    boolean hearted,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
