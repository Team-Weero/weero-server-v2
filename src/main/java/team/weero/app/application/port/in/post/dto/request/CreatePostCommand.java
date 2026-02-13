package team.weero.app.application.port.in.post.dto.request;

import java.util.UUID;

public record CreatePostCommand(String title, String content, UUID userId) {}
