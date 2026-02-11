package team.weero.app.application.port.in.post.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetPostInfo(
    UUID id,
    String title,
    String content,
    String nickName,
    int viewCount,
    int heartCount,
    boolean hearted,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
