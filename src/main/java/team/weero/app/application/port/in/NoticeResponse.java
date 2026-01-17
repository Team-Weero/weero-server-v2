package team.weero.app.application.port.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoticeResponse(
    UUID id, String title, String content, UUID writerId, LocalDateTime createdAt) {}
