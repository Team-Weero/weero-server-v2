package team.weero.app.adapter.in.web.notice.dto.request;

import java.util.UUID;

public record UpdateNoticeCommand(UUID id, String title, String content) {}
