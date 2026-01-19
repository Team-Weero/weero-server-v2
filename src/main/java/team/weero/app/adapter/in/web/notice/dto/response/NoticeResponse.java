package team.weero.app.adapter.in.web.notice.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoticeResponse(
    UUID id, String title, String content, UUID writerId, LocalDateTime createdAt) {}
