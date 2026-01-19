package team.weero.app.adapter.in.web.post.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetPostResponse(
    UUID id,
    String title,
    String content,
    String studentName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
