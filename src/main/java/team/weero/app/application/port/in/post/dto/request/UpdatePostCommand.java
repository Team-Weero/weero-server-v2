package team.weero.app.application.port.in.post.dto.request;

import java.util.UUID;

public record UpdatePostCommand(UUID postId, UUID userId, String title, String content) {}
