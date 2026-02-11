package team.weero.app.application.port.in.notice.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoticeInfo(
    UUID id, String title, String content, UUID writerId, LocalDateTime createdAt) {}
